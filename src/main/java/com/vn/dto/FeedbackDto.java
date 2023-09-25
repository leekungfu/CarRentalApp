package com.vn.dto;

import com.vn.entities.Booking;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FeedbackDto {
    private String feedbackId;
    private String bookingId;
    private Double rating;
    private String content;
    private LocalDateTime dateTime;
    private BookingDto booking;
}
