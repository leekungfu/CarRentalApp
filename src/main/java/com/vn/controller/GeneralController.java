package com.vn.controller;

import com.vn.dto.MessageResult;
import com.vn.entities.Member;
import com.vn.service.MemberService;
import com.vn.service.impl.CustomUserDetails;
import com.vn.utils.Utility;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:3000")
public class GeneralController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private Utility utility;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<?> signup(@RequestParam String email, @RequestParam String password, @RequestParam String phone, @RequestParam String fullName, @RequestParam String role) {
        Member checkMem = memberService.findByEmail(email);
        if (checkMem == null) {
            Member member = new Member();
            member.setEmail(email);
            member.setPassword(bCryptPasswordEncoder.encode(password));
            member.setPhone(phone);
            member.setFullName(fullName);
            member.setRole(role);

            memberService.save(member);

            setAuthen(member);

            return ResponseEntity.ok(member);
        }
        return ResponseEntity.ok(new MessageResult("FAILED"));
    }

    private CustomUserDetails setAuthen(Member member) {
        CustomUserDetails customUserDetails = new CustomUserDetails(member);
        Authentication authentication = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return (CustomUserDetails) authentication.getPrincipal();
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        Member result = memberService.findByEmail(email);
        if (result != null) {
            Member member = setAuthen(result).getMember();
            return ResponseEntity.ok(member);
        }
        return ResponseEntity.ok(new MessageResult("FAILED"));
    }

    @PostMapping("/logout")
    @ResponseBody
    public ResponseEntity<HttpStatus> logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, authentication);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/forgot_password")
    public String forgotPassForm() {

        return "account/forgot_password";
    }

    @PostMapping("/forgot_password")
    public String forgotPassProcessing(@ModelAttribute("member") Member member, Model model, HttpServletRequest request) {
        // Find existed email in database to confirm sending the reset password request
        String email = request.getParameter("email");
        // Random token chain, which will be used to determine ex
        String token = RandomString.make(30);

        try {
            // Set value for user found by the given email and persist change to the DB
            memberService.updateResetPasswordToken(token, email);

            // Send the reset link with unique token which will expired immediately user reset password success
            String resetPassLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
            utility.sendEmail(email, resetPassLink);

        } catch (UsernameNotFoundException e) {
            model.addAttribute("error", e.getMessage());
        } catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error", "Something wrong while sending email!");
        }

        return "account/forgot_password";
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
}
