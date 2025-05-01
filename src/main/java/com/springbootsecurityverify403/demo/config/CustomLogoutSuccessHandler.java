package com.springbootsecurityverify403.demo.config;

import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, 
                                org.springframework.security.core.Authentication authentication) 
                                throws IOException {
        new SecurityContextLogoutHandler().logout(request, response, authentication);
        request.getSession().invalidate();
        response.sendRedirect("/");
    }
}

