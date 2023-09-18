package com.vn.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupDto {
    private String fullName;
    private String email;
    private String password;
    private String phone;
    private String role;
}
