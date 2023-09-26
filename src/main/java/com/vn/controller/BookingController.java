package com.vn.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.vn.enums.BookingStatus;
import com.vn.enums.CarStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.vn.entities.Booking;
import com.vn.entities.Car;
import com.vn.entities.Member;
import com.vn.service.BookingService;
import com.vn.service.CarService;
import com.vn.service.MemberService;
import com.vn.service.impl.CustomUserDetails;

@RestController
@RequestMapping()
public class BookingController {
	
    @Autowired
    CarService carService;
    
    @Autowired
    MemberService memberService;

	@Autowired
	BookingService bookingService;

	@PostMapping("/booking_result")
	public String bookingSuccessful(@ModelAttribute("payment")Integer paymentMethod,
									@ModelAttribute("startDate") String startDate,
									@ModelAttribute("endDate") String endDate,
									HttpSession httpSession,
									Model model){
		
		Booking booking = new Booking();
		
		Car car = (Car) httpSession.getAttribute("carModel");
		car.setStatus(CarStatus.Booked);
		carService.update(car);
		
		booking.setCar(car);
//		booking.setPaymentMethod(paymentMethod);
		booking.setStartDate(LocalDateTime.parse(startDate));
		booking.setEndDate(LocalDateTime.parse(endDate));
		
		switch(paymentMethod) {
		case 1:
			booking.setBookingStatus(BookingStatus.Confirmed);
			break;
		case 2:
		case 3:
			booking.setBookingStatus(BookingStatus.Pending_deposit);
			break;
		default: 
			break;
		}
		
		CustomUserDetails detail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Member> optional = memberService.findUserById(detail.member().getId());
		if(optional.isEmpty()) {
			return "redirect:/login";
		} else {
			booking.setMember(optional.get());
		}
		
		bookingService.save(booking);
		
		model.addAttribute("fullName", detail.member().getFullName());
		model.addAttribute("booking", booking);
		return "booking/booking_successful";
	}
	
	@GetMapping("/booking")
	public String bookingDetail(Model model,
								@RequestParam(name = "booking_id") Integer bookingId) {
		Booking booking = bookingService.findById(bookingId);
		model.addAttribute("booking", booking);
		
		CustomUserDetails detail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("fullName", detail.member().getFullName());
		return "booking/booking_detail";
	}
	
//	@GetMapping("/my_booking")
//	public String bookingsListByMember(Model model) {
//
//		CustomUserDetails detail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//		List<Booking> bookings = bookingService.findAllByMemberId(detail.member().getId());
//		model.addAttribute("bookings", bookings);
//
//		model.addAttribute("fullName", detail.member().getFullName());
//
//		return "booking/booking_list";
//	}
}
