package com.gdu.dev_springboot_demo.controller;

import com.gdu.dev_springboot_demo.model.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    @GetMapping("/error")
    public String formLogin(Model model) {
        model.addAttribute("user", new Users());
        return "/error";
    }
}
