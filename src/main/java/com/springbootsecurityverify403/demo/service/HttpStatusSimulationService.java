package com.springbootsecurityverify403.demo.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class HttpStatusSimulationService {
    private static HttpStatus httpStatus = HttpStatus.OK;
    
    public static void setHttpStatus(HttpStatus httpStatus) {
        HttpStatusSimulationService.httpStatus = httpStatus;
    }

    public static HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
