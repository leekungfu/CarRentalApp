package com.vn.controller;

import com.vn.dto.BookingDto;
import com.vn.dto.ResponseCarResult;
import com.vn.dto.ResponseMessage;
import com.vn.dto.ResponseSearchCar;
import com.vn.entities.Booking;
import com.vn.entities.Car;
import com.vn.entities.Member;
import com.vn.entities.MemberTransaction;
import com.vn.enums.CarStatus;
import com.vn.enums.PaymentMethod;
import com.vn.service.BookingService;
import com.vn.service.CarService;
import com.vn.service.MemberService;
import com.vn.service.impl.CustomUserDetails;
import lombok.AllArgsConstructor;
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
    public ResponseEntity<ResponseSearchCar> searchCarByProvince(@RequestParam("selectedProvince") String province, @RequestParam("startTime") String startTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
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
    public ResponseEntity<?> addBooking(@ModelAttribute BookingDto dto) {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member member = customUserDetails.member();
        if (dto.getPaymentMethod().equals("wallet")) {
            member.setWallet(Double.valueOf(dto.getDeposit()));
            memberService.updateMember(member);
        }
        Car result = carService.findCarById(Integer.valueOf(dto.getCarId()));
        if (result == null) {
            return ResponseEntity.ok(new ResponseMessage(false, "Car is not exist"));
        }
        result.setStatus(CarStatus.Booked);
        Booking booking = new Booking();
        booking.setCar(result);
        booking.setPaymentMethod(PaymentMethod.valueOf(dto.getPaymentMethod()));
        booking.setStartDate(LocalDateTime.parse(dto.getStartDate()));
        booking.setEndDate(LocalDateTime.parse(dto.getEndDate()));
        booking.setMember(member);
        bookingService.save(booking);

        return ResponseEntity.ok(new ResponseMessage(true, "OK"));
    }
}
