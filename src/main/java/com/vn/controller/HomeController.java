package com.vn.controller;

import com.vn.entities.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value = {"/", "home"})
    public String getHomePage(Model model) {
        Member member = new Member();
        member.setFullName("Sava");
        member.setEmail("levansang.gthn@gmail.com");
        member.setId(1);
        member.setRole("CUSTOMER");
        model.addAttribute("user", member);

        switch (member.getRole()) {
            case "CUSTOMER":
                return "/home/home_customer";
            case "CAR_OWNER":
                return "/home/home_car_owner";
            default:
                return "/home/home_guest";
        }
    }
}
