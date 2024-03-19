package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @GetMapping("/basic")
    public String getBasics() {
        return "This should be basic login!";
    }

    @GetMapping("/header")
    public String getHeaders(Authentication authentication) {
        return "Hi %s - This should be a private page with custom header login!".formatted(authentication.getPrincipal());
    }
}
