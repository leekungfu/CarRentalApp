package com.vn.utils;


public class ParaSecurity {
    public static String[] ignoreSecurityPages = {
            "/forgot_password",
            "/reset_password",
            "/signupAjax",
            "/loginAjax",
            "/home_guest",
            "/about",
            "/images/**",
            "/assets/css/**",
            "/assets/js/**",
            "/assets/icon/**",
            "/assets/boostrap/css/**",
            "/assets/boostrap/js/**",
            "/booking_process",
            "/images/**",
            "/search/car",
            "/detail_car",
            "/rent_car",
            "/home",
            "/editProfile",
            "/ChangePassword"
    };

    public static String[] customerPages ={
            "/home/",
            "/home_customer",
            "/customer_profile",
            "/customer_booking",
            "/customer_wallet",
            "/logout",
            "/about",
            "/editProfile",
            "/ChangePassword"

    };
    public static String[] carOwnerPages ={
            "/home/",
            "/home_car_owner",
            "/add_car",
            "/owner_profile",
            "/owner_cars",
            "/owner_wallet",
            "/owner_reports",
            "/logout",
            "/about",
            "/editProfile",
            "/about",
            "/booking/*",
            "/car/*",
            "/booking/status",
            "/ChangePassword"
    };
}
