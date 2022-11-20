package com.vn.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vn.entities.Booking;
import com.vn.entities.Car;
import com.vn.entities.Member;
import com.vn.service.BookingService;
import com.vn.service.CarService;
import com.vn.service.MemberService;
import com.vn.service.impl.CustomUserDetails;

@Controller
public class BookingController {
	
	@Autowired
	MemberService memberSerivce;
	
	@Autowired
	CarService carService;

	@Autowired
	BookingService bookingService;
	
	@GetMapping("/rent_car")
	public String bookingProcess(Model model,
								@RequestParam(name = "carID") Integer carID,
								HttpSession httpSession) {
		Car car = carService.findById(carID);
		model.addAttribute("car", car);
		
		httpSession.setAttribute("car", car);
		return "booking/booking_process";
	}
	
	@PostMapping("/booking_result")
	public String bookingSuccessful(@ModelAttribute("payment")Integer paymentMethod,
									HttpSession httpSession,
									Model model){
		
		Booking booking = new Booking();
		
		Car car = (Car) httpSession.getAttribute("car");
		booking.setCar(car);
		booking.setPaymentMethod(paymentMethod);
		
		Member member = new Member();
		CustomUserDetails detail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Member> optional = memberSerivce.findUserById(detail.getId());
		if(optional.isEmpty()) {
			return "redirect:/login";
		} else member = optional.get();
		booking.setMember(member);
		
		bookingService.addBooking(booking);
		
		model.addAttribute("booking", booking);
		return "booking/booking_successful";
	}
	
	@GetMapping("/booking")
	public String bookingDetail(Model model,
								@RequestParam(name = "booking_id") Integer bookingId) {
		Booking booking = bookingService.findBookingById(bookingId);
		model.addAttribute(booking);
		return "booking/booking_detail";
	}
	
	@GetMapping("/my_booking")
	public String bookingsListByMember(Model model,
										@RequestParam(name="userID") Integer memberId) {
//		String memberName = null;
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		if(auth.isAuthenticated()) {
//			memberName = auth.;
//		}
//		Member member = memberRepository.findByName(memberName);
		List<Booking> bookings = bookingService.findAllByMember(memberId);
		model.addAttribute("bookings", bookings);
		
		return "booking/booking_list";
	}
}
