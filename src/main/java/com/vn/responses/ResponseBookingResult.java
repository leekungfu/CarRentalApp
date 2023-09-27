package com.vn.responses;

import com.vn.dto.BookingDto;
import com.vn.entities.Booking;
import lombok.Data;

@Data
public class ResponseBookingResult {
    private Boolean isSuccess;
    private String message;
    private BookingDto booking;

    public ResponseBookingResult(Boolean isSuccess, String message, BookingDto booking) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.booking = booking;
    }
}
