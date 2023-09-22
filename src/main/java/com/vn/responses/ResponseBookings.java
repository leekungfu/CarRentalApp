package com.vn.responses;

import com.vn.dto.BookingDto;
import com.vn.entities.Booking;
import lombok.Data;

import java.util.List;

@Data
public class ResponseBookings {
    private Boolean isSuccess;
    private String message;
    private List<BookingDto> bookings;

    public ResponseBookings(Boolean isSuccess, String message, List<BookingDto> bookings) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.bookings = bookings;
    }
}
