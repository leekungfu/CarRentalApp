package com.vn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("home")
    public String getHomeCustomer(){
        return "/home/home_customer";
    }

    @GetMapping("home_car_owner")
    public String getHomeCarOwner(){
        return "/home/home_car_owner";
    }
}
