package com.vn.utils;


public class ParaSecurity {
    public static String[] ignoreSecurityPages = {
            "/",
            "/forgot_password",
            "/reset_password",
            "/signup",
            "/login",
            "/images/**",
    };

    public static String[] customerPages ={
            "/logout",
            "/personalInfo",
            "/updatePassword",


    };
    public static String[] carOwnerPages ={
            "/logout",
            "/personalInfo",
            "/updatePassword",
            "/addCar",
    };
}
