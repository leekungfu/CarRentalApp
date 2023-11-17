package com.vn.controller;

import com.vn.config.JwtTokenService;
import com.vn.dto.*;
import com.vn.entities.DriverInformation;
import com.vn.entities.MemberTransaction;
import com.vn.enums.Type;
import com.vn.responses.ResponseMemberResult;
import com.vn.entities.Member;
import com.vn.enums.Role;
import com.vn.responses.ResponseMessage;
import com.vn.responses.ResponseTransactionResult;
import com.vn.responses.ResponseTransactions;
import com.vn.service.DriverInformationService;
import com.vn.service.MemberService;
import com.vn.service.MemberTransactionService;
import com.vn.service.impl.CustomUserDetails;
import com.vn.utils.ImageUtil;
import com.vn.utils.Utility;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
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
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class GeneralController {
    private final MemberService memberService;
    private final Utility utility;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenService jwtTokenService;
    private final MemberTransactionService memberTransactionService;
    private final DriverInformationService driverInformationService;

    @GetMapping("/currentUser")
    @ResponseBody
    public ResponseEntity<?> getCurrentUser() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member member = memberService.findByEmail(customUserDetails.getUsername());
        return ResponseEntity.ok(member);
    }

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<?> signup(@ModelAttribute @NotNull SignupDto dto) {
        Member checkMem = memberService.findByEmail(dto.getEmail());
        if (checkMem == null) {
            String token = jwtTokenService.generateToken(dto.getEmail());
            Member member = new Member();
            member.setEmail(dto.getEmail());
            member.setPassword(dto.getPassword());
            member.setPhone(dto.getPhone());
            member.setFullName(dto.getFullName());
            member.setRole(Role.valueOf(dto.getRole()));
            // save method including encode password
            memberService.save(member);
            setAuth(member);
            MemberDto memberDto = member.toDto();
            return ResponseEntity.ok(new ResponseMemberResult(true, "Sign up successful!", memberDto, token));
        }
        return ResponseEntity.ok(new ResponseMemberResult(false, "Sign up failed! Email is taken.", null, null));
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
                MemberDto memberDto = result.toDto();
                return ResponseEntity.ok(new ResponseMemberResult(true, "Login successful!", memberDto, token));
            }
            return ResponseEntity.ok(new ResponseMemberResult(false, "Wrong password! Please try again", null));
        }
        return ResponseEntity.ok(new ResponseMemberResult(false, "Email is not exist! Please try again.", null));
    }

    @PostMapping("/logout")
    @ResponseBody
    public ResponseEntity<?> logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, authentication);
        return ResponseEntity.ok(new ResponseMemberResult(true, "None", null));
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
            MemberDto memberDto = result.toDto();
            return ResponseEntity.ok(new ResponseMemberResult(true, "Update successful!", memberDto));
        }
        return ResponseEntity.ok(new ResponseMemberResult(false, "Update failed! Check your information again please.", null));
    }

//    @PostMapping("/saveDriver")
//    @ResponseBody
//    public ResponseEntity<?> saveDriver(@ModelAttribute MemberDto dto, @RequestParam("drivingLicense") MultipartFile drivingLicense) {
//        DriverInformation driver = new DriverInformation();
//        driver.setFullName(dto.getFullName());
//        driver.setBirthDay(LocalDate.parse(dto.getBirthDay()));
//        driver.setPhone(dto.getPhone());
//        driver.setNationalID(dto.getNationalID());
//        driver.setProvince(dto.getProvince());
//        driver.setDistrict(dto.getDistrict());
//        driver.setWard(dto.getWard());
//        driver.setStreet(dto.getStreet());
//        driver.setDrivingLicense(ImageUtil.saveImage(drivingLicense));
//            driverInformationService.save(driver);
//            DriverInformationDto driverDto = driver.toDto();
//            return ResponseEntity.ok(new ResponseMemberResult(true, "Update successful!", driverDto));
//
//    }

    @PostMapping("/updatePassword")
    @ResponseBody
    public ResponseEntity<?> changePassword(@RequestParam String email, @RequestParam String password) {
        Member result = memberService.findByEmail(email);
        if (result != null) {
            result.setPassword(bCryptPasswordEncoder.encode(password));
            memberService.updateMember(result);
            MemberDto memberDto = result.toDto();
            return ResponseEntity.ok(new ResponseMemberResult(true, "Change password successful!", memberDto));
        } else {
            return ResponseEntity.ok(new ResponseMemberResult(false, "Change password failed! Try again please", null));
        }
    }

    @PostMapping("/newTransaction")
    @ResponseBody
    public ResponseEntity<?> create(@RequestParam Double amount, @RequestParam String type) {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        Member member = memberService.findByEmail(customUserDetails.getUsername());
        MemberTransaction memberTransaction = new MemberTransaction();
        double balance = member.getWallet() != null ? member.getWallet() : 0.0;
        if (type.equals("Top_up")) {
            member.setWallet(balance + amount);
            memberTransaction.setType(Type.Top_up);
        } else if (type.equals("Withdraw")) {
            member.setWallet(balance - amount);
            memberTransaction.setType(Type.Withdraw);
        }
        memberTransaction.setMember(member);
        memberTransaction.setDateTime(LocalDateTime.now());
        memberTransaction.setAmount(amount);
        memberService.updateMember(member);
        memberTransactionService.save(memberTransaction);
        return ResponseEntity.ok(new ResponseTransactionResult(true, "Deposit money successfully!", memberTransaction.toDto()));
    }

    @GetMapping("/transactionList")
    @ResponseBody
    public ResponseEntity<?> getTransaction(@RequestParam String fromTime, @RequestParam String toTime) {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<MemberTransactionDto> list = memberTransactionService.getByDate(customUserDetails.member().getId(), LocalDateTime.parse(fromTime, formatter), LocalDateTime.parse(toTime, formatter));
        return ResponseEntity.ok(new ResponseTransactions(true, "OK", list));
    }

    @PostMapping("/forgot_password")
    @ResponseBody
    public ResponseEntity<?> forgotPassProcessing(@RequestParam String email, HttpServletRequest request) {
        // Random token chain, which will be used to determine ex
        Member result = memberService.findByEmail(email);
        if (result == null) {
            return ResponseEntity.ok(new ResponseMemberResult(false, "Email is not exist!", null));
        }
        String token = RandomString.make(30);
        try {
            // Set value for user found by the given email and persist change to the DB
            memberService.updateResetPasswordToken(token, email);
            // Send the reset link with unique token which will expired immediately user reset password success
            String resetPassLink = Utility.getSiteURL(request) + "?token=" + token;
            utility.sendEmail(email, resetPassLink);

        } catch (UsernameNotFoundException | UnsupportedEncodingException | MessagingException e) {
            return ResponseEntity.ok(new ResponseMemberResult(false, e.getMessage(), null));
        }
        MemberDto memberDto = result.toDto();
        return ResponseEntity.ok(new ResponseMemberResult(true, "Sent!", memberDto));
    }

    @PostMapping("/reset_password")
    @ResponseBody
    public ResponseEntity<?> resetPassProcessing(@RequestParam("token") String token, @RequestParam("password") String password) {
        Member member = memberService.findByResetPasswordToken(token);
        if (member == null) {
            return ResponseEntity.ok(new ResponseMessage(false, "The request is expired!"));
        } else {
            memberService.updatePassword(member, password);
            return ResponseEntity.ok(new ResponseMessage(true, "Reset password successfully!"));
        }
    }

    @GetMapping("/")
    public String home() {

        return "index";
    }
}
