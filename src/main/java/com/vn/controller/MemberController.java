package com.vn.controller;

import com.vn.entities.Member;
import com.vn.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
public class MemberController {
    @Autowired
    MemberService memberService;
//    @GetMapping(value = {"/", ""})
//    public String home() {
//
//        return "Home";
//    }

//    @GetMapping("/editProfile")
//    public String editProfile() {
////        Optional<Member> memberEdit = memberService.findUserById(id);
////   memberEdit.isPresent(user-> model.addAttribute("user",user)) ;
//
//        return "editProfile";
//    }
//    @Controller
//    public class SecurityController {
//
//        @RequestMapping(value = "/username", method = RequestMethod.GET)
//        @ResponseBody
//        public String currentUserName(Principal principal) {
//            return principal.getName();
//        }
//    }
}
