package com.vn.utils;


public class ParaSecurity {
    public static String[] ignoreSecurityPages = {
            "/forgot_password",
            "/reset_password",
            "/api/signup",
            "/api/login",
            "/api/logout",
//            "/api/owner/files",
//            "/api/owner/files/{id}",
    };

    public static String[] customerPages ={
            "/api/personalInfo",
            "/api/updatePassword",
    };
    public static String[] carOwnerPages ={
            "/api/personalInfo",
            "/api/updatePassword",
            "/api/owner/**"
    };
}
