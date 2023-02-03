package com.vn.controller;

import com.vn.service.impl.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String getHomePage(Model model) {
        CustomUserDetails detail;
        try {
            detail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            long countRoleCustomer = detail.getAuthorities().stream().filter(x -> x.getAuthority().contains("CUSTOMER")).count();
            model.addAttribute("fullName", detail.getMember().getFullName());
            if (countRoleCustomer > 0) return "home/home_customer";
            return "home/home_car_owner";
        } catch (Exception e) {
            return "home/home_guest";
        }
    }

    @GetMapping("/home_guest")
    public String homeGuestPage() {
        return "home/home_guest";
    }
}
