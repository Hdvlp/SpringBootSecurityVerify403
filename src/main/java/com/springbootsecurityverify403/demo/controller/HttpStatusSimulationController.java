package com.springbootsecurityverify403.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springbootsecurityverify403.demo.service.HttpStatusSimulationService;

@RestController
public class HttpStatusSimulationController {

    @GetMapping("/setStatus")
    public String getSetStatus(@RequestParam int status) {
        String ret = "";
        if (status == 200){
            HttpStatusSimulationService.setHttpStatus(HttpStatus.OK);
            ret = "200";
        }
        if (status == 500){
            HttpStatusSimulationService.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            ret = "500";
        }
        return ret;
    }
    

}
