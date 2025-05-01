package com.springbootsecurityverify403.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class LogoutController {

    @GetMapping("/logout")
    public void getLogout(HttpServletResponse response) throws IOException {
        response.sendRedirect("/login");
    }

}
