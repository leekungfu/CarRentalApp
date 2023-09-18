package com.vn.responses;

import com.vn.entities.Booking;
import lombok.Data;

@Data
public class ResponseBookingResult {
    private Boolean isSuccess;
    private String message;
    private Booking booking;

    public ResponseBookingResult(Boolean isSuccess, String message, Booking booking) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.booking = booking;
    }
}
