package com.vn.controller;


import com.vn.entities.Member;
import com.vn.service.MemberService;
import com.vn.utils.Utility;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    @GetMapping("/signup")
    public String signUp() {
        return "home/home_guest";
    }

    @PostMapping("/signup")
    public String signUpPage(@ModelAttribute("member")Member member, Model model, HttpServletRequest request) {

        Member checkMem = memberService.findUserByEmailAndFullName(member.getEmail(), member.getFullName());
        if (checkMem != null) {
            model.addAttribute("msg", "Email is taken!");
            return "home/home_guest";
        }
        memberService.save(member);

        // Auto login
        List<GrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(member.getRole());
        authorities.add(authority);
        User u = new User(member.getEmail(), member.getPassword(), authorities);

        Authentication authentication = new UsernamePasswordAuthenticationToken(u, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/home";
    }

    @GetMapping("/login")
    public String signIn() {
        return "home/home_guest";
    }

    @PostMapping("/login")
    public String signInPage() {
            return "redirect:/home";
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

        // The notification popup will be displayed as "Invalid token" if the token not be found in the DB.
        Member member = memberService.findByResetPasswordToken(token);

        if (member == null) {
            model.addAttribute("message", "Invalid token");
            return "account/reset_password";
        } else {
            memberService.updatePassword(member, password);
            model.addAttribute("message", "Reset password successfully!");
        }

        return "account/reset_password_success";
    }

    @GetMapping("/logout")
    public String logOut() {
        return "home/home_guest";
    }

}
