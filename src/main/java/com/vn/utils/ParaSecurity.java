package com.vn.utils;


public class ParaSecurity {
    public static String[] ignoreSecurityPages = {
            "/api/forgot_password",
            "/api/reset_password",
            "/api/signup",
            "/api/login",
            "/api/logout"
    };

    public static String[] customerPages ={
            "/api/customer/**",
    };
    public static String[] carOwnerPages ={
            "/api/owner/**",
    };
}
