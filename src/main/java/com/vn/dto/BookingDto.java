package com.vn.dto;

import lombok.Data;

@Data
public class BookingDto {
    private String carId;
    private String paymentMethod;
    private String startDate;
    private String endDate;
    private String deposit;
}
