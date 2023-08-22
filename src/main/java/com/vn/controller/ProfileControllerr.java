package com.vn.controller;

import com.vn.dto.MemberDto;
import com.vn.entities.Member;
import com.vn.service.MemberService;
import com.vn.service.impl.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:3000")
public class ProfileControllerr {
    @Autowired
    private MemberService memberService;

    @GetMapping("/changePassword")
    public String resetPassword(Model model) {
        CustomUserDetails detail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member m = memberService.findByEmail(detail.getUsername());
        model.addAttribute("fullName", detail.getMember().getFullName());
        model.addAttribute("user", m);
        return "account/changePassword";
    }

    @PostMapping("/changePassword")
    public String resetPassword(@ModelAttribute("user") Member member) {
        CustomUserDetails detail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member m = memberService.findByEmail(detail.getUsername());
        m.setPassword(member.getPassword());
        memberService.save(m);
        return "redirect:/home";
    }

    @GetMapping("/editProfile")
    public String editProfile(Model model) {
        CustomUserDetails detail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member m = memberService.findByEmail(detail.getUsername());
        model.addAttribute("fullName", detail.getMember().getFullName());
        model.addAttribute("user", m);
        return "account/editProfile";
    }



}
