package com.vn.controller;

import com.vn.entities.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("home")
    public String getHomeCustomer(Model model){
        Member member= new Member();
        member.setFullName("Sava");
        member.setEmail("levansang.gthn@gmail.com");
        member.setId(1);
        model.addAttribute("user", member);
        return "/home/home_customer";
    }
}
