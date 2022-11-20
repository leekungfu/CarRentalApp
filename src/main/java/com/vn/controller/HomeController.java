package com.vn.controller;

import com.vn.entities.Member;
import com.vn.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/home")
    public String getHomePage(Model model) {
        // Use UserDetails to authorize and redirect new User to exactly home page with their role
        // Do not use CustomUserDetails!!!
        UserDetails detail;
        try {
            detail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Member member = memberService.findByEmail(detail.getUsername());
            model.addAttribute("user", member);
            long countRoleCustomer = detail.getAuthorities().stream().filter(x -> {
                return x.getAuthority().contains("CUSTOMER");
            }).count();

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

    @GetMapping("/home_customer")
    public String homeCustomerPage() {
        return "home/home_customer";
    }

    @GetMapping("/home_car_owner")
    public String homeCarOwnerPage() {
        return "home/home_car_owner";
    }

}
