package com.vn.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vn.entities.Booking;

@Service
public interface BookingService {
	List<Booking> findAllByMemberId(Integer userId);
	
	Booking findBookingById(Integer bookingId);
	
	void save(Booking booking);
	
	Boolean deleteBooking(Integer bookingId);
	
	Booking updateBooking(Booking booking);
}
