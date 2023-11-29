//package com.vn.entities;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.time.LocalDate;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//public class DriverInformation {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//    private String fullName;
//    private LocalDate birthDay;
//    private String phone;
//    private String email;
//    private String nationalID;
//    private String street;
//    private String province;
//    private String district;
//    private String ward;
//    private String drivingLicense;
//    @OneToOne
//    @JoinColumn(name = "booking_id")
//    private Booking booking;
//}
