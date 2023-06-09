package com.example.springsecuritydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Romic
 * @date 2023/6/9
 * @description
 */
@Controller
public class LoginController {
    @GetMapping("/login")
    public String toLogin() {
        return "login";
    }
}
