package com.springbootsecurityverify403.demo.config;


import java.io.IOException;
import java.util.Objects;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    public void doFilterInternal(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain chain
        ) throws ServletException, IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (Objects.equals(httpRequest, null)){
            chain.doFilter(request, response);
            return;
        }
        String requestPath = httpRequest.getRequestURI();
        System.out.println(String.format("JwtAuthenticationFilter requestPath: Enter the path %s", requestPath));

        String token = request.getHeader("Authorization");

        if (Objects.equals(token, null)) {
            System.out.println(String.format("JwtAuthenticationFilter requestPath: Enter the path %s with token value of null", 
                requestPath));
            chain.doFilter(request, response);
            return;
        }

        if (!token.startsWith("Bearer ")) {
            System.out.println(String.format("JwtAuthenticationFilter requestPath: Enter the path %s with token value not beginning with Bearer ", 
                requestPath));
            chain.doFilter(request, response);
            return;
        }


        // not implemented other JWT logic
        // chain.doFilter(request, response);
        return;

    }

}
