package com.vn.controller;

import java.time.LocalDate;
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
    CarService carService;
    
    @Autowired
    MemberService memberService;

	@Autowired
	BookingService bookingService;
	
	
	//rent-a-car
	@GetMapping("/rent_car")
	public String bookingProcess(Model model,
								@RequestParam(name = "carID") Integer carID,
								HttpSession httpSession) {
		Car car = carService.findById(carID);
		model.addAttribute("car", car);
		httpSession.setAttribute("carModel", car);
		
		CustomUserDetails detail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("user", detail.member());
		
		model.addAttribute("fullName", detail.member().getFullName());
		return "booking/booking_process";
		
	}
	
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
		booking.setPaymentMethod(paymentMethod);
		booking.setStartDate(LocalDateTime.parse(startDate));
		booking.setEndDate(LocalDateTime.parse(endDate));
		
		switch(paymentMethod) {
		case 1:
			booking.setBookingStatus(BookingStatus.CONFIRMED);
			break;
		case 2:
		case 3:
			booking.setBookingStatus(BookingStatus.PENDING_DEPOSIT);
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
		
		bookingService.addBooking(booking);
		
		model.addAttribute("fullName", detail.member().getFullName());
		model.addAttribute("booking", booking);
		return "booking/booking_successful";
	}
	
	@GetMapping("/booking")
	public String bookingDetail(Model model,
								@RequestParam(name = "booking_id") Integer bookingId) {
		Booking booking = bookingService.findBookingById(bookingId);
		model.addAttribute("booking", booking);
		
		CustomUserDetails detail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("fullName", detail.member().getFullName());
		return "booking/booking_detail";
	}
	
	@GetMapping("/my_booking")
	public String bookingsListByMember(Model model) {
		
		CustomUserDetails detail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		List<Booking> bookings = bookingService.findAllByMemberId(detail.member().getId());
		model.addAttribute("bookings", bookings);
		
		model.addAttribute("fullName", detail.member().getFullName());
		
		return "booking/booking_list";
	}
}
