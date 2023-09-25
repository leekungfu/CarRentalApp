package com.vn.responses;

import com.vn.entities.Booking;
import com.vn.entities.Feedback;
import lombok.Data;

@Data
public class ResponseFeedbackResult {
    private Boolean isSuccess;
    private String message;
    private Feedback feedback;

    public ResponseFeedbackResult(Boolean isSuccess, String message, Feedback feedback) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.feedback = feedback;
    }
}
