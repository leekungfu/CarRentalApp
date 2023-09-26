package com.vn.repository;

import com.vn.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    Feedback findFeedbackByBookingId(Integer bookingId);
}
