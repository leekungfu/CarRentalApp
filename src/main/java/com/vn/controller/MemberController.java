package com.vn.controller;

import com.vn.entities.Member;
import com.vn.service.MemberService;
import com.vn.service.impl.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;

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
import java.security.Principal;

@Controller
public class MemberController {
    @Autowired
    MemberService memberService;

    @GetMapping("/editProfile")
    public String updateProfile(Model model) {

        CustomUserDetails detail;

        detail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Member member = memberService.findByEmail(detail.getMember().getEmail());
        Member member = memberService.findById(detail.getMember().getId());
        member.setEmail(detail.getMember().getEmail());

//        System.out.println("====================/n==============" + member);
        model.addAttribute("user", member);

        return "/editProfile";
    }

    @PostMapping("/editProfile")
    public String upadteProfile(@ModelAttribute("user") Member user, Model model
            , @RequestParam("pdrivingLicense") MultipartFile drivingLicense) throws IOException {
        String drivingLicenses = StringUtils.cleanPath(drivingLicense.getOriginalFilename());
        user.setDrivingLicense(drivingLicenses);

        CustomUserDetails detail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Member m = memberService.findById(detail.getMember().getId());
        m.setFullName(user.getFullName());
        m.setBirthDay(user.getBirthDay());
        m.setPhone(user.getPhone());
        m.setNationalID(user.getNationalID());
        m.setCity(user.getCity());
        m.setDistrict(user.getDistrict());
        m.setWard(user.getWard());
        m.setStreet(user.getStreet());
        m.setDrivingLicense(user.getDrivingLicense());

        memberService.updateMember(m);

        String uploadDir = "./src/main/resources/static/images/";
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {

            Files.createDirectories(uploadPath);

        }
        try (InputStream inputStream = drivingLicense.getInputStream()) {
            Path path = uploadPath.resolve(drivingLicenses);
            System.out.println(path.toFile().getAbsolutePath());
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            throw new IOException("Can not save file " + drivingLicenses);
        }
        model.addAttribute("user", m);

        return "/editProfile";
    }

}
