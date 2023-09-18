package com.vn.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vn.entities.Booking;
import com.vn.repository.BookingRepository;
import com.vn.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService{

	@Autowired
	BookingRepository bookingRepository;
	
	@Override
	public List<Booking> findAllByMemberId(Integer memberId) {
		return bookingRepository.findAllByMemberId(memberId);
	}

	@Override
	public Booking findBookingById(Integer bookingId) {
		return bookingRepository.findById(bookingId).orElse(null);
	}

	@Override
	public void save(Booking booking) {
		bookingRepository.save(booking);
	}

	@Override
	public Boolean deleteBooking(Integer bookingId) {
		bookingRepository.deleteById(bookingId);
		return true;
	}

	@Override
	public Booking updateBooking(Booking booking) {
		return bookingRepository.save(booking);
	}

}
