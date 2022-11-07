package com.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.vn.service.BookingService;

@Controller
public class BookingController {

	@Autowired
	BookingService bookingService;
	
	@GetMapping("booking")
	public String bookingPage() {
		
		return "booking/booking_process";
	}
	
	@PostMapping("booking")
	public String bookingSuccess() {
		return "booking/booking_success";
	}
}
