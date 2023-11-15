package com.vn.controller;

import com.vn.dto.BookingDto;
import com.vn.dto.CarDto;
import com.vn.dto.FeedbackDto;
import com.vn.dto.MemberTransactionDto;
import com.vn.entities.*;
import com.vn.enums.BookingStatus;
import com.vn.enums.Type;
import com.vn.responses.*;
import com.vn.enums.CarStatus;
import com.vn.enums.PaymentMethod;
import com.vn.service.*;
import com.vn.service.impl.CustomUserDetails;
import com.vn.utils.ImageUtil;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class CustomerController {
    private final CarService carService;
    private final BookingService bookingService;
    private final MemberService memberService;
    private final FeedbackService feedbackService;
    private final MemberTransactionService memberTransactionService;
    private final DriverInformationService driverInformationService;

    @GetMapping("/searchCar")
    @ResponseBody
    public ResponseEntity<ResponseSearchCar> searchCarByProvince(@RequestParam("selectedProvince") String province, @RequestParam("startTimeFormatted") String startTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time = LocalDateTime.parse(startTime, formatter);
        List<Car> cars = carService.searchCar(province, time);
        return ResponseEntity.ok(new ResponseSearchCar(true, "Search car successful", cars));
    }

    @GetMapping("/getCar/{id}")
    @ResponseBody
    public ResponseEntity<ResponseCarResult> getCarById(@PathVariable Integer id) {
        Car car = carService.findCarById(id);
        if (car == null) {
            return ResponseEntity.ok(new ResponseCarResult(false, "Get car failed", null, null));
        }
        return ResponseEntity.ok(new ResponseCarResult(true, "Get car successful", car, null));
    }

    @PostMapping("/addBooking")
    @ResponseBody
    public ResponseEntity<?> addBooking(@ModelAttribute @NotNull BookingDto dto) {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member member = customUserDetails.member();
        Double balance = member.getWallet();
        Car result = carService.findCarById(Integer.valueOf(dto.getCarId()));
        if (result == null) {
            return ResponseEntity.ok(new ResponseMessage(false, "Car is not exist"));
        }
        if (result.getStatus().equals(CarStatus.Booked)) {
            return ResponseEntity.ok(new ResponseMessage(false, "This car has already been rented! Please try other one."));
        }
        Booking booking = new Booking();
        MemberTransaction memberTransaction = new MemberTransaction();
        if (dto.getPaymentMethod().equals("Wallet")) {
            if (balance == null || balance < Double.parseDouble(dto.getDeposit())) {
                return ResponseEntity.ok(new ResponseMessage(false, "Your wallet is not enough money to do this payment!"));
            }
            member.setWallet(balance - Double.parseDouble(dto.getDeposit()));
            memberService.updateMember(member);
            memberTransaction.setAmount(Double.valueOf(dto.getDeposit()));
            memberTransaction.setType(Type.Pay_deposit);
            booking.setBookingStatus(BookingStatus.Confirmed);
        } else {
            booking.setBookingStatus(BookingStatus.Pending_deposit);
        }
        result.setStatus(CarStatus.Booked);
        booking.setId(Integer.valueOf(dto.getBookingId()));
        booking.setCar(result);
        booking.setPaymentMethod(PaymentMethod.valueOf(dto.getPaymentMethod()));
        booking.setStartDate(LocalDateTime.parse(dto.getStartDate()));
        booking.setEndDate(LocalDateTime.parse(dto.getEndDate()));
        booking.setMember(member);
        bookingService.save(booking);
        memberTransaction.setBooking(booking);
        memberTransaction.setMember(member);
        memberTransaction.setDateTime(LocalDateTime.now());
        memberTransactionService.save(memberTransaction);

        DriverInformation driver = new DriverInformation();
        driver.setEmail(dto.getEmail());
        driver.setFullName(dto.getFullName());
        driver.setBirthDay(dto.getBirthDay());
        driver.setPhone(dto.getPhone());
        driver.setNationalID(dto.getNationalID());
        driver.setProvince(dto.getProvince());
        driver.setDistrict(dto.getDistrict());
        driver.setWard(dto.getWard());
        driver.setStreet(driver.getStreet());
        driver.setDrivingLicense(ImageUtil.saveImage(dto.getDrivingLicense()));
        driver.setBooking(booking);
        driverInformationService.save(driver);

        BookingDto bookingDto = booking.toDto();
        return ResponseEntity.ok(new ResponseBookingResult(true, "Payment successfully!", bookingDto));
    }

    @GetMapping("/bookings")
    @ResponseBody
    public ResponseEntity<?> BookingList() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<BookingDto> bookings = bookingService.findAllByMemberId(customUserDetails.member().getId());
        return ResponseEntity.ok(new ResponseBookings(true, "Get bookings successful", bookings));
    }

    @GetMapping("/booking/{id}")
    @ResponseBody
    public ResponseEntity<?> SingleBooking(@PathVariable Integer id) {
        Booking booking = bookingService.findById(id);
        BookingDto dto = booking.toDto();
        return ResponseEntity.ok(new ResponseBookingResult(true, "Get booking successful", dto));
    }

    @PostMapping("/updateBookingStatus/{id}")
    @ResponseBody
    public ResponseEntity<?> updateBookingStatus(@PathVariable Integer id, @RequestParam String status, @RequestParam String plateNumber, @RequestParam Double cost) {
        Booking booking = bookingService.findById(id);
        BookingDto dto;
        Car car = carService.findCarByLicensePlate(plateNumber);
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member member = customUserDetails.member();
        MemberTransaction memberTransaction = new MemberTransaction();
        if (booking == null) {
            return ResponseEntity.ok(new ResponseMessage(false, "Booking is not exist!"));
        }
        if (booking.getBookingStatus().equals(BookingStatus.valueOf(status))) {
            switch (status) {
                case "Completed" -> {
                    return ResponseEntity.ok(new ResponseMessage(false, "This car had already been returned!"));
                }
                case "Cancelled" -> {
                    return ResponseEntity.ok(new ResponseMessage(false, "This car had already been cancelled!"));
                }
                case "In_Progress" -> {
                    return ResponseEntity.ok(new ResponseMessage(false, "This car had been confirmed pick up and could not be cancelled!"));
                }
            }
        }
        booking.setBookingStatus(BookingStatus.valueOf(status));
        bookingService.update(booking);
        dto = booking.toDto();
        if (status.equals("Completed")) {
            car.setStatus(CarStatus.Available);
            carService.update(car);
            double currentBalance = member.getWallet() != null ? member.getWallet() : 0;
            if (cost > 0) {
                member.setWallet(currentBalance + Math.abs(cost));
                memberService.updateMember(member);
                booking.setMember(member);
                bookingService.update(booking);
                dto = booking.toDto();
                memberTransaction.setAmount(Math.abs(cost));
                memberTransaction.setType(Type.Offset_final_payment);
                memberTransaction.setDateTime(LocalDateTime.now());
                memberTransaction.setMember(member);
                memberTransaction.setBooking(booking);
                memberTransactionService.save(memberTransaction);
                return ResponseEntity.ok(new ResponseBookingResult(true, "Pay successful!", dto));
            } else {
                if (currentBalance >= cost) {
                    member.setWallet(currentBalance - Math.abs(cost));
                    memberService.updateMember(member);
                    booking.setMember(member);
                    bookingService.update(booking);
                    dto = booking.toDto();
                    memberTransaction.setAmount(Math.abs(cost));
                    memberTransaction.setType(Type.Deduct_final_payment);
                    memberTransaction.setDateTime(LocalDateTime.now());
                    memberTransaction.setMember(member);
                    memberTransaction.setBooking(booking);
                    memberTransactionService.save(memberTransaction);
                    return ResponseEntity.ok(new ResponseBookingResult(true, "Pay successful!", dto));
                } else {
                    return ResponseEntity.ok(new ResponseBookingResult(false, "The current balance is not enough to do this action! Please top-up and try again.", dto));
                }
            }
        }
        return ResponseEntity.ok(new ResponseBookingResult(true, "Update booking status successful", dto));
    }

    @PostMapping("/addFeedback")
    @ResponseBody
    public ResponseEntity<?> setRating(@ModelAttribute FeedbackDto dto) {
        Feedback feedback = new Feedback();
        Booking booking = bookingService.findById(Integer.valueOf(dto.getBookingId()));
        if (booking == null) {
            return ResponseEntity.ok(new ResponseMessage(false, "The booking is not exist!"));
        }
        if (booking.getFeedback() != null) {
            return ResponseEntity.ok(new ResponseMessage(false, "You had already made a feedback for this booking!"));
        }
        feedback.setRating(dto.getRating());
        feedback.setContent(dto.getContent());
        feedback.setDateTime(LocalDateTime.now());
        feedback.setBooking(booking);
        booking.setFeedback(feedback);

        feedbackService.save(feedback);
        bookingService.update(booking);
        return ResponseEntity.ok(new ResponseFeedbackResult(true, "Send feedback successful!", feedback));
    }
}
