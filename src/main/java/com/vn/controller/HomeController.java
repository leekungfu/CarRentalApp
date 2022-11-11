package com.vn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
<<<<<<< HEAD
    @GetMapping(value = {"home/customer", "", "/"})
=======
    @GetMapping("home")
>>>>>>> main
    public String getHomeCustomer(){
        return "home/home_customer";
    }
}
