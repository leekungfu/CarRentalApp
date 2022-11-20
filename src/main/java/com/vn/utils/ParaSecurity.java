package com.vn.utils;


public class ParaSecurity {
    public static String[] ignoreSecurityPages = {
            "/forgot_password",
            "/reset_password",
            "/signup",
            "/login",
            "/home_logout",
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
            "/assets/fonts/flaticon/font/**",
            "/assets/fonts/icomoon/**",
            "/assets/fonts/ionicons/css/**",
            "/assets/fonts/ionicons/fonts/**",
            "/assets/fonts/open-iconic/**",
            "/assets/scss/bootstrap/**",
            "/assets/scss/bootstrap/mixins/**",
            "/assets/scss/bootstrap/utilities/**",
            "/assets/scss/bootstrap/vendor/**",
            "/listCar",
            "/booking/status",
            "/car/payment"
    };

    public static String[] customerPages ={
            "/home/",
            "/home_customer/",
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
            "/home_owner/",
            "/forgot_password",
            "/reset_password",
            "/add_car",
            "/owner_profile",
            "/owner_cars",
            "/owner_wallet",
            "/owner_reports",
            "/logout",
            "/about",
            "/booking/*",
            "/car/*",
            "/listCar",
            "/home_car_owner",
            "/booking/status"
    };
}
