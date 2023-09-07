package com.vn.utils;


public class ParaSecurity {
    public static String[] ignoreSecurityPages = {
            "/forgot_password",
            "/reset_password",
            "/api/signup",
            "/api/login",
            "/api/logout"
    };

    public static String[] customerPages ={
            "/api/personalInfo",
            "/api/updatePassword",
    };
    public static String[] carOwnerPages ={
            "/api/personalInfo",
            "/api/updatePassword",
            "/api/owner/addCar",
    };
}
