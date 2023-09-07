package com.vn.controller;

import com.vn.config.JwtTokenService;
import com.vn.dto.LoginDto;
import com.vn.dto.MemberDto;
import com.vn.dto.MessageResult;
import com.vn.dto.SignupDto;
import com.vn.entities.Member;
import com.vn.enums.Role;
import com.vn.service.MemberService;
import com.vn.service.impl.CustomUserDetails;
import com.vn.utils.ImageUtil;
import com.vn.utils.Utility;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class GeneralController {
    private final MemberService memberService;
    private final Utility utility;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<?> signup(@ModelAttribute @NotNull SignupDto dto) {
        Member checkMem = memberService.findByEmail(dto.getEmail());
        if (checkMem == null) {
            Member member = new Member();
            member.setEmail(dto.getEmail());
            member.setPassword(dto.getPassword());
            member.setPhone(dto.getPhone());
            member.setFullName(dto.getFullName());
            member.setRole(Role.valueOf(dto.getRole()));

            String token = jwtTokenService.generateToken(dto.getEmail());

            // save method including encode password
            memberService.save(member);
            setAuth(member);
            return ResponseEntity.ok(new MessageResult(true, "Sign up successful!", member, token));
        }
        return ResponseEntity.ok(new MessageResult(false, "Sign up failed! Email is taken.", null, null));
    }

    private void setAuth(Member member) {
        CustomUserDetails customUserDetails = new CustomUserDetails(member);
        Authentication authentication = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        authentication.getPrincipal();
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(@ModelAttribute @NotNull LoginDto dto, HttpServletRequest request) throws ServletException {
        Member result = memberService.findByEmail(dto.getEmail());
        if (result != null) {
            String storedPassword = result.getPassword();
            String dtoPassword = dto.getPassword();
            if (bCryptPasswordEncoder.matches(dtoPassword, storedPassword)) {
                request.login(dto.getEmail(), dto.getPassword());
                String token = jwtTokenService.generateToken(dto.getEmail());
                return ResponseEntity.ok(new MessageResult(true, "Login successful!", result, token));
            }
            return ResponseEntity.ok(new MessageResult(false, "Wrong password! Please try again", null));
        }
        return ResponseEntity.ok(new MessageResult(false, "Email is not exist! Please try again.", null));
    }

    @PostMapping("/logout")
    @ResponseBody
    public ResponseEntity<?> logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, authentication);
        return ResponseEntity.ok(new MessageResult(true, "None", null));
    }

    @PostMapping("/personalInfo")
    @ResponseBody
    public ResponseEntity<?> updateInfo(@ModelAttribute @NotNull MemberDto dto,
                                        @RequestParam("drivingLicense")
                                        @NotNull MultipartFile drivingLicense) {
        Member result = memberService.findByEmail(dto.getEmail());
        if (result != null) {
            result.setFullName(dto.getFullName());
            result.setBirthDay(LocalDate.parse(dto.getBirthDay()));
            result.setPhone(dto.getPhone());
            result.setNationalID(dto.getNationalID());
            result.setProvince(dto.getProvince());
            result.setDistrict(dto.getDistrict());
            result.setWard(dto.getWard());
            result.setStreet(dto.getStreet());
            result.setDrivingLicense(ImageUtil.saveImage(drivingLicense));
            memberService.updateMember(result);
            return ResponseEntity.ok(new MessageResult(true, "Update successful!", result));
        }
        return ResponseEntity.ok(new MessageResult(false, "Update failed! Check your information again please.", null));

    }

    @PostMapping("/updatePassword")
    @ResponseBody
    public ResponseEntity<?> changePassword(@RequestParam String email, @RequestParam String password) {
        Member result = memberService.findByEmail(email);
        if (result != null) {
            result.setPassword(bCryptPasswordEncoder.encode(password));
            memberService.updateMember(result);
            return ResponseEntity.ok(new MessageResult(true, "Change password successful!", result));
        } else {
            return ResponseEntity.ok(new MessageResult(false, "Change password failed! Try again please", null));
        }
    }

    @PostMapping("/forgot_password")
    @ResponseBody
    public ResponseEntity<?> forgotPassProcessing(@RequestParam String email, HttpServletRequest request) {
        // Random token chain, which will be used to determine ex
        Member result = memberService.findByEmail(email);
        if (result == null) {
            return ResponseEntity.ok(new MessageResult(false, "Email is not exist!", null));
        }
        String token = RandomString.make(30);
        try {
            // Set value for user found by the given email and persist change to the DB
            memberService.updateResetPasswordToken(token, email);
            // Send the reset link with unique token which will expired immediately user reset password success
            String resetPassLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
            utility.sendEmail(email, resetPassLink);

        } catch (UsernameNotFoundException | UnsupportedEncodingException | MessagingException e) {
            return ResponseEntity.ok(new MessageResult(false, e.getMessage(), null));
        }
        return ResponseEntity.ok(new MessageResult(true, "Sent!", result));
    }

    @GetMapping("/reset_password")
    public String resetPassForm(@Param(value = "token") String token, Model model) {

        // Check for the validity of the token in the URL, to make sure that only user who got the real email can change their password.
        // The notification popup will be displayed as the message below if user had changed password successfully with the reset link in their mailbox.
        Member member = memberService.findByResetPasswordToken(token);
        model.addAttribute("token", token);
        if (member == null) {
            model.addAttribute("message", "The request is expired! Please send a new request to reset your password by entering your email again on previous step!");
            return "account/reset_password";
        }

        return "account/reset_password";
    }

    @PostMapping("/reset_password")
    public String resetPassProcessing(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        // The notification popup will be displayed as "The request is expired!" if the token not be found in the DB.
        Member member = memberService.findByResetPasswordToken(token);

        if (member == null) {
            model.addAttribute("message", "The request is expired!");
            return "account/reset_password";
        } else {
            memberService.updatePassword(member, password);
            model.addAttribute("message", "Reset password successfully!");
        }

        return "account/reset_password_success";
    }

    @GetMapping("/")
    public String home() {

        return "index";
    }
}
