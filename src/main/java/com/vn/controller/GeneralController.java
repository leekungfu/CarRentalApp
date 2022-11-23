package com.vn.controller;

import com.vn.dto.StringMessageDTO;
import com.vn.entities.Member;
import com.vn.service.MemberService;
import com.vn.service.impl.CustomUserDetails;
import com.vn.utils.Utility;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.stream.Collectors;

@Controller
public class GeneralController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private Utility utility;


    @GetMapping("/about")
    public String aboutPage() {
        return "home/about";
    }

    @GetMapping("/signupAjax")
    public String signUp() {
        return "home/home_guest";

    }

    @PostMapping("/signupAjax")
    @ResponseBody
    public ResponseEntity<?> signUpPageAjax(@RequestBody Member member) {
        Member checkMem = memberService.findByEmail(member.getEmail());
        if (checkMem == null) {
            memberService.save(member);

            // Auto login after user signed up successfully
            CustomUserDetails customUserDetails = new CustomUserDetails(member);
            Authentication authentication = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok(new StringMessageDTO("YES"));
        }
        return ResponseEntity.ok(new StringMessageDTO("NO"));
    }

    @PostMapping("/loginAjax")
    @ResponseBody
    public ResponseEntity<?> loginPageAjax(@RequestBody Member member, HttpServletRequest request) {
        try {
            request.login(member.getEmail(), member.getPassword());
            return ResponseEntity.ok(new StringMessageDTO("OK"));
        } catch (Exception exception) {
            return ResponseEntity.ok(new StringMessageDTO("FAILED"));
        }
    }






    @PostMapping("/signupAjax")
    @ResponseBody
    public ResponseEntity<?> signUpPageAjax(@RequestBody Member member, Errors errors) {
        Member checkMem = memberService.findByEmail(member.getEmail());
        if (checkMem == null) {
            memberService.save(member);
            CustomUserDetails customUserDetails = new CustomUserDetails(member);
            Authentication authentication = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok(new StringMessageDTO("YES"));
        }
        return ResponseEntity.ok(new StringMessageDTO("NO"));
    }







    @GetMapping("/login")
    public String loginPage(){
        return "home/home_guest";
    }

    @GetMapping("/forgot_password")
    public String forgotPassForm() {

        return "account/forgot_password";
    }

    @PostMapping("/forgot_password")
    public String forgotPassProcessing(@ModelAttribute("member") Member member,
                                       Model model,
                                       HttpServletRequest request) {
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
