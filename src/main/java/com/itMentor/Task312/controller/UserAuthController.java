package com.itMentor.Task312.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserAuthController {
    @GetMapping("/home")
    public String getHomePage() {
        return "home";
    }

    @GetMapping("/admin/home")
    public String getAdminHomePage() {
        return "adminPage";
    }

    @GetMapping("/user/home")
    public String getUserHomePage() {
        return "userPage";
    }
}
