package com.example.websocketsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CsrfController {

    @RequestMapping("/csrf")
    @PreAuthorize("permitAll()")
    public CsrfToken csrf(CsrfToken token) {
        return token;
    }
}