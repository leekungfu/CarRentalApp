package com.vn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
        @GetMapping(value= {"/",""})
        public String home() {

            return "/home/home_guest";
        }
}
