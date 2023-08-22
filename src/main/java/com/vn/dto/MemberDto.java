package com.vn.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vn.entities.Booking;
import com.vn.entities.Car;
import com.vn.entities.MemberTransaction;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class MemberDto {
    private String email;
    private String fullName;
    private String birthDay;
    private String phone;
    private String nationalID;
    private String city;
    private String district;
    private String ward;
    private String street;
    private MultipartFile drivingLicense;
}
