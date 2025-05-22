package com.example.bookreviewapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";  // Will return the login.html page located in src/main/resources/templates
    }
}
