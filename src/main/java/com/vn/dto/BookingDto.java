package com.vn.dto;

import com.vn.entities.Car;
import com.vn.entities.DriverInformation;
import com.vn.entities.Feedback;
import com.vn.entities.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
    private String carId;
    private String paymentMethod;
    private String startDate;
    private String endDate;
    private String deposit;
    private String bookingStatus;
    private CarDto car;
    private MemberDto member;
    private DriverInformation info;
    private Feedback feedback;
}
