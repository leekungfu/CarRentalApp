package com.vn.controller;

import com.vn.entities.Member;
import com.vn.service.MemberService;
import com.vn.service.impl.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;


@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:3000")
public class ProfileController {
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
        model.addAttribute("user",m);
        return "account/editProfile";
    }

    @PostMapping("/personalInfo")
    @ResponseBody
    public String editProfileProcess(@ModelAttribute("member") Member member, Model model
            , @RequestParam("drivingLicense") MultipartFile drivingLicense) throws IOException {
        String drivingLicenses = StringUtils.cleanPath(drivingLicense.getOriginalFilename());
//        member.setDrivingLicense(drivingLicenses);
        CustomUserDetails detail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member m = memberService.findByEmail(detail.getUsername());
        m.setFullName(member.getFullName());
        m.setBirthDay(member.getBirthDay());
        m.setPhone(member.getPhone());
        m.setNationalID(member.getNationalID());
        m.setCity(member.getCity());
        m.setDistrict(member.getDistrict());
        m.setWard(member.getWard());
        m.setStreet(member.getStreet());
        m.setDrivingLicense(member.getDrivingLicense());
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
        return "account/editProfile";
    }

}
