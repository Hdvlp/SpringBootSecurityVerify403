package com.springbootsecurityverify403.demo.config;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import org.springframework.stereotype.Component;

import com.springbootsecurityverify403.demo.service.CustomUserDetailsService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RoleBasedSuccessHandler implements AuthenticationSuccessHandler  {
    @Autowired
    private final CustomUserDetailsService customUserDetailsService;

    public RoleBasedSuccessHandler(CustomUserDetailsService customUserDetailsService){
        this.customUserDetailsService = customUserDetailsService;
    }

    public boolean hasRole(UserDetails userDetails, String role) {
        return userDetails.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(role));
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

                String email = authentication.getName();

                UserDetails userDetails = (UserDetails) customUserDetailsService.loadUserByUsername(email);
                
                if (hasRole(userDetails, "ROLE_ADMIN")){
                    response.sendRedirect("/admin");
                }

                if (hasRole(userDetails, "ROLE_EDITOR")){
                    response.sendRedirect("/editor");
                }

                if (hasRole(userDetails, "ROLE_USER")){
                    response.sendRedirect("/user");
                }
       
    }
}