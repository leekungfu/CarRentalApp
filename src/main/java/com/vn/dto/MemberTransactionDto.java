package com.vn.dto;

import lombok.Data;

@Data
public class MemberTransactionDto {
    private Integer id;
    private Double amount;
    private String type;
    private String dateTime;
    private CarDto car;
    private MemberDto member;
    private BookingDto booking;
}
