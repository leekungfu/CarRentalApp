package com.vn.controller;

import com.vn.dto.BookingDto;
import com.vn.dto.CarDto;
import com.vn.enums.BookingStatus;
import com.vn.responses.*;
import com.vn.entities.Booking;
import com.vn.entities.Car;
import com.vn.entities.Member;
import com.vn.enums.CarStatus;
import com.vn.enums.PaymentMethod;
import com.vn.service.BookingService;
import com.vn.service.CarService;
import com.vn.service.MemberService;
import com.vn.service.impl.CustomUserDetails;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class CustomerController {
    private final CarService carService;
    private final BookingService bookingService;
    private final MemberService memberService;

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
        Booking booking = new Booking();
        if (dto.getPaymentMethod().equals("Wallet")) {
            if (balance == null || balance < Double.parseDouble(dto.getDeposit())) {
                return ResponseEntity.ok(new ResponseMessage(false, "Your wallet is not enough money to do this payment!"));
            }
            member.setWallet(balance - Double.parseDouble(dto.getDeposit()));
            memberService.updateMember(member);

            booking.setBookingStatus(BookingStatus.Confirmed);
        }
        else {
            booking.setBookingStatus(BookingStatus.Pending_deposit);
        }
        result.setStatus(CarStatus.Booked);
        booking.setCar(result);
        booking.setPaymentMethod(PaymentMethod.valueOf(dto.getPaymentMethod()));
        booking.setStartDate(LocalDateTime.parse(dto.getStartDate()));
        booking.setEndDate(LocalDateTime.parse(dto.getEndDate()));
        booking.setMember(member);
        bookingService.save(booking);

        return ResponseEntity.ok(new ResponseBookingResult(true, "Payment successfully!", booking));
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
        Booking booking = bookingService.findBookingById(id);
        return ResponseEntity.ok(new ResponseBookingResult(true, "Get booking successful", booking));
    }
}
