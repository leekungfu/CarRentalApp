package com.vn.service;

import com.vn.dto.FeedbackDto;
import com.vn.entities.Feedback;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FeedbackService {
    Feedback getFeedbackByBookingId(Integer bookingId);
    List<FeedbackDto> getAllFeedbacks();
    void save(Feedback feedback);
}
