package com.vn.controller;

import com.vn.entities.Member;
import com.vn.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class MemberController {
    @Autowired
    MemberService memberService;
    @GetMapping("/editProfile")
    public String updateProfile( Model model , Member member) {

//        UserDetails detail;
//
//        detail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        memberService.findByEmail(member.getEmail());
        member.getFullName();
        member.getEmail();



        model.addAttribute("user", member);


        return "/editProfile";
    }

    @PostMapping("/editProfile")
    public String upadteProfile(@ModelAttribute("member") Member member) {
    memberService.updateMember(member);
        return "/editProfile";
    }



}
