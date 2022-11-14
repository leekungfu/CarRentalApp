package com.vn.controller;

import com.vn.entities.Member;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value = {"/", "home"})
    public String getHomePage(Model model) {
        UserDetails detail;
        try {
            detail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            long countRoleCustomer = detail.getAuthorities().stream().filter(x -> {
                return x.getAuthority().contains("CUSTOMER");
            }).count();

            long countRoleOWNER = detail.getAuthorities().stream().filter(x -> {
                return x.getAuthority().contains("OWNER");
            }).count();
            Member member = new Member();
            member.setFullName("Sava");
            member.setEmail("levansang.gthn@gmail.com");
            member.setId(1);
            member.setRole("CUSTOMER");
            model.addAttribute("user", member);

            if (countRoleCustomer > 0) return "home/home_customer";
            return "home/home_car_owner";

        } catch (Exception e) {
            return "home/home_guest";
        }
    }
}
