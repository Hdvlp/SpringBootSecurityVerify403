package com.springbootsecurityverify403.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.springbootsecurityverify403.demo.service.HttpStatusSimulationService;
import com.springbootsecurityverify403.demo.service.UserService;

@Controller
public class LoginController {

    private UserService userService;
    LoginController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<String> postAuthLogin() {
        System.out.println("POST /auth/login");
        return this.userService.handle(HttpStatusSimulationService.getHttpStatus());
    }

    @GetMapping("/login")
    public String loginPage() {
        // name of the Thymeleaf template (e.g., login.html)
        // in src/main/resources/templates/login.html
        return "login";
    }

}

