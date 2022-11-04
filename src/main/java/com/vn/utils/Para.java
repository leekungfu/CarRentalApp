package com.vn.utils;


import java.util.ArrayList;

public class Para {
    public static String[] ignoreSecurityPages = {
            "/home/customer",
            "/forgot_password",
            "/reset_password",
            "/signup",
            "/signin",
            "/home",
            "/assets/css/**",
            "/assets/js/**",
            "/assets/icon/**",
            "/assets/boostrap/css/**",
            "/assets/boostrap/js/**",
    };

    public static String[] customerPages ={
            "/home/",
            "/forgot_password",
            "/reset_password",
            "/customer_profile",
            "/customer_booking",
            "/customer_wallet",
            "/logout",
            "/about"
    };
    public static String[] carOwnerPages ={
            "/home/",
            "/forgot_password",
            "/reset_password",
            "/add_car",
            "/owner_profile",
            "/owner_cars",
            "/owner_wallet",
            "/owner_reports",
            "/logout",
            "/about"
    };
}
