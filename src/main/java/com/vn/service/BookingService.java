package com.vn.service;

import java.time.LocalDateTime;
import java.util.List;

import com.vn.dto.BookingDto;
import org.springframework.stereotype.Service;

import com.vn.entities.Booking;

@Service
public interface BookingService {
	List<BookingDto> findAllByMemberId(Integer userId);

	Booking findById(Integer bookingId);
	
	void save(Booking booking);
	
	Boolean delete(Integer bookingId);
	
	Booking update(Booking booking);
}
