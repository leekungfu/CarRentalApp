package com.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.vn.service.BookingService;

@Controller
public class BookingController {

	@Autowired
	BookingService bookingService;
	
	@GetMapping("/booking_process")
	public String bookingProcess() {
		return "booking/booking_process";
	}
	
}
