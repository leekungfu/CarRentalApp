package com.vn.utils;


public class ParaSecurity {
    public static String[] ignoreSecurityPages = {
            "/forgot_password",
            "/reset_password",
            "/api/signup",
            "/api/login",
            "/images/**",
    };

    public static String[] customerPages ={
            "/logout",
            "/api/personalInfo",
            "/api/updatePassword",


    };
    public static String[] carOwnerPages ={
            "/logout",
            "/api/personalInfo",
            "/api/updatePassword",
            "/api/owner/addCar",
    };
}
