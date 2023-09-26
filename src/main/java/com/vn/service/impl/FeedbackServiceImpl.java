package com.vn.service.impl;

import com.vn.dto.FeedbackDto;
import com.vn.entities.Feedback;
import com.vn.repository.FeedbackRepository;
import com.vn.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository repository;
    @Override
    public Feedback getFeedbackByBookingId(Integer bookingId) {
        return repository.findFeedbackByBookingId(bookingId);
    }

    @Override
    public List<FeedbackDto> getAllFeedbacks() {
        return null;
    }

    @Override
    public void save(Feedback feedback) {
        repository.save(feedback);
    }
}
