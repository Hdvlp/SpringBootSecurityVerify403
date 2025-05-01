package com.springbootsecurityverify403.demo.service;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.springbootsecurityverify403.demo.model.User;
import com.springbootsecurityverify403.demo.repo.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;
    
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    
    public String register(User userToRegister){
        String s = encoder.encode(userToRegister.getPassword());

        userToRegister.setPassword("{bcrypt}"+s);

        int number = 0;
        try
        {
            User newUser =  repo.save(userToRegister) ;
            number = newUser.getId();
        }
        catch (NumberFormatException e)
        {
            number = 0;
        }
        if (number == 0){
            return new String ("Error...");
        }
        return new String ("OK. <a href=\"/login\">Please log in here</a>.");
    }

    public ResponseEntity<String> handle(HttpStatus httpStatus) {
        Map<HttpStatus, String> map = new HashMap<>();
        map.put(HttpStatus.OK, "<span style=\"background: #dcf0b6;\">Simulation: Default logged in page: Status code 200</span>");
        map.put(HttpStatus.INTERNAL_SERVER_ERROR, "<span style=\"background: #f9e5db;\">Simulation: Default internal server error:  Status code 500</span>");

        return ResponseEntity.status(httpStatus)
            .body(map.get(httpStatus));
    }
}

