package com.example.springsecuritydemo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Romic
 * @date 2023/6/9
 * @description
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PreAuthorize("hasAnyAuthority('all')")
    @GetMapping("/user")
    public String getUser() {
        return "I'm user";
    }
}
