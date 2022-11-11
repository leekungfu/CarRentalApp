package com.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.vn.service.BookingService;

@Controller
public class BookingController {

	@Autowired
	BookingService bookingService;
	
}
