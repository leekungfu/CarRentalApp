package com.vn.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class MemberDto {
    private String email;
    private String fullName;
    private String birthDay;
    private String phone;
    private String nationalID;
    private String province;
    private String district;
    private String ward;
    private String street;
    private String role;
    private String wallet;
    private MultipartFile drivingLicense;
}
