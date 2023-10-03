package com.vn.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.vn.dto.BookingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vn.entities.Booking;
import com.vn.repository.BookingRepository;
import com.vn.service.BookingService;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;

    @Override
    public List<BookingDto> findAllByMemberId(Integer memberId) {
        List<Booking> bookings = bookingRepository.findAllByMemberId(memberId);
        List<BookingDto> dtoList = bookings.stream().map(Booking::toDto).toList();
        return dtoList;
    }

    @Override
    public Booking findById(Integer bookingId) {
        return bookingRepository.findById(bookingId).orElse(null);
    }

    @Override
    public void save(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    public Boolean delete(Integer bookingId) {
        bookingRepository.deleteById(bookingId);
        return true;
    }

    @Override
    public Booking update(Booking booking) {
        return bookingRepository.save(booking);
    }
}
