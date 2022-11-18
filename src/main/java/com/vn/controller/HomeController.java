package com.vn.controller;

import com.vn.entities.Member;
import com.vn.utils.Const;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @GetMapping( "/home")
    public String getHomePage(Model model, HttpSession session) {
        UserDetails detail;
        try {
            detail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            Member member = new Member();
            member.setFullName(detail.getUsername());
            model.addAttribute("user", member);
            long countRoleCustomer = detail.getAuthorities().stream().filter(x -> {
                return x.getAuthority().contains("CUSTOMER");
            }).count();

            if (countRoleCustomer > 0) {
                return "home/home_customer";
            }
            session.setAttribute(Const.SESSION_ROLE_CAR_OWNER, member);
            return "home/home_car_owner";

        } catch (Exception e) {
            return "home/home_guest";
        }
    }
    @GetMapping("/home_guest")
    public String homeGuestPage() {
        return "home/home_guest";
    }

    @GetMapping("/home_car_owner")
    public String homeCarOwnerPage() {
        return "home/home_car_owner";
    }

}
