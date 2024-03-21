package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @GetMapping("/basic")
    public String getBasics(Authentication authentication) {
        return "Hi %s - This should be a private page with BASIC authentication!".formatted(getName(authentication));
    }

    @GetMapping("/header")
    public String getHeaders(Authentication authentication) {
        return "Hi %s - This should be a private page with custom HEADER authentication!".formatted(getName(authentication));
    }

    @GetMapping("/header-new")
    public String newHeader(Authentication authentication) {
        return "Hi %s - This should be a private page with custom PRE-AUTHENTICATED HEADER authentication!".formatted(getName(authentication));
    }

    private String getName(Authentication authentication) {
        return authentication == null ? "Anonymous" : authentication.getName();
    }
}
