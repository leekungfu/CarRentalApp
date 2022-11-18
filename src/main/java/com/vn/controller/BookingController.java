package com.vn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vn.entities.Booking;
import com.vn.entities.Car;
import com.vn.entities.Member;
import com.vn.repository.MemberRepository;
import com.vn.service.BookingService;
import com.vn.service.CarService;

@Controller
public class BookingController {
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	CarService carService;

	@Autowired
	BookingService bookingService;
	
	@GetMapping("/rent_car")
	public String bookingProcess(Model model,
								@RequestParam(name = "carID") Integer carID) {
		Car car = carService.findById(carID);
		model.addAttribute("car", car);
		
//		String memberName = null;
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		if(auth.isAuthenticated()) {
//			memberName = auth.getName();
//		}
//		Member member = memberRepository.findByName(memberName);
//		model.addAttribute("renter", member);
		
		return "booking/booking_process";
	}
	
	@PostMapping("/booking_result")
	public String bookingSuccessful(Model model) {
		return "booking/booking_successful";
	}
	
	@GetMapping("/booking/{id}")
	public String bookingDetail(Model model,
								@PathVariable(name = "booking_id")Integer bookingId) {
		Booking booking = bookingService.findBookingById(bookingId);
		model.addAttribute(booking);
		return "booking/booking_detail";
	}
	
	@GetMapping("/all_bookings")
	public String bookingsListByMember(Model model) {
//		String memberName = null;
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		if(auth.isAuthenticated()) {
//			memberName = auth.;
//		}
//		Member member = memberRepository.findByName(memberName);
//		List<Booking> bookings = bookingService.findAllBookingsByMember(member.getId());
//		model.addAttribute("bookings", bookings);
//		
		return "booking/booking_list";
	}
}
