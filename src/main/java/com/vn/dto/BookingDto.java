package com.vn.dto;

import com.vn.entities.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
    private String bookingId;
    private String carId;
    private String paymentMethod;
    private String startDate;
    private String endDate;
    private String deposit;
    private String bookingStatus;
    private CarDto car;
    private MemberDto member;
    private DriverInformation info;
    private Integer id;
    private String fullName;
    private LocalDate birthDay;
    private String phone;
    private String email;
    private String nationalID;
    private String street;
    private String province;
    private String district;
    private String ward;
    private MultipartFile drivingLicense;
    private Booking booking;
}
