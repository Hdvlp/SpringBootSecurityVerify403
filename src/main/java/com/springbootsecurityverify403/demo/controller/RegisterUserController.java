package com.springbootsecurityverify403.demo.controller;

import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springbootsecurityverify403.demo.model.User;
import com.springbootsecurityverify403.demo.service.UserService;


@RestController
public class RegisterUserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public String register(@RequestParam Map<String, String> body){   
        User userToRegister = new User(); 
        String tmpEmail = body.get("email").trim();
        String tmpFirstName = body.get("firstname").trim();
        String tmpLastName = body.get("lastname").trim();
        String tmpPassword = body.get("password").trim();
        String tmpRoleToRegister = body.get("role").trim();

        final int EMAIL_LIMIT = 320;
        final int PASSWORD_LIMIT = 80;
        final int MIN_CHAR_LIMIT_EMAIL = 5;
        final int MIN_CHAR_LIMIT_PASSWORD = 8;
        final int CHAR_LIMIT_FIRSTNAME_OR_LASTNAME = 80;
        final int MIN_CHAR_LIMIT_FIRSTNAME_OR_LASTNAME = 1;

        if (tmpEmail.length() > EMAIL_LIMIT){
            return String.format("Email is longer than %d characters.", EMAIL_LIMIT);
        }

        if (tmpFirstName.length() > CHAR_LIMIT_FIRSTNAME_OR_LASTNAME){
            return String.format("First name is longer than %d characters.", CHAR_LIMIT_FIRSTNAME_OR_LASTNAME);
        }

        if (tmpLastName.length() > CHAR_LIMIT_FIRSTNAME_OR_LASTNAME){
            return String.format("Last name is longer than %d characters.", CHAR_LIMIT_FIRSTNAME_OR_LASTNAME);
        }
            
        if (tmpPassword.length() > PASSWORD_LIMIT){
            return String.format("Password is longer than %d characters.", PASSWORD_LIMIT);
        }

            
        String allowStrInEmail = "@_-.abcdefghijklmnopqrstuvwxyz1234567890";
        String allowStrInPassword = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890_@+=";
        String allowStrInFirstNameOrLastName = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890- ";


        String tmpEmailSanitized = "";
        String tmpFirstNameSanitized = "";
        String tmpLastNameSanitized = "";
        String tmpPasswordSanitized = "";

        if(tmpEmail.length() < MIN_CHAR_LIMIT_EMAIL){
            return String.format("Error: The number of characters in the email is less than %d.", MIN_CHAR_LIMIT_EMAIL);
        }

        if(tmpFirstName.length() < MIN_CHAR_LIMIT_FIRSTNAME_OR_LASTNAME){
            return String.format("Error: The number of characters in the first name is less than %d.", MIN_CHAR_LIMIT_FIRSTNAME_OR_LASTNAME);
        }

        if(tmpLastName.length() < MIN_CHAR_LIMIT_FIRSTNAME_OR_LASTNAME){
            return String.format("Error: The number of characters in the last name is less than %d.", MIN_CHAR_LIMIT_FIRSTNAME_OR_LASTNAME);
        }

        if(tmpPassword.length() < MIN_CHAR_LIMIT_PASSWORD){
            return String.format("Error: The number of characters in the password is less than %d.", MIN_CHAR_LIMIT_PASSWORD);
        }
            

        for (int i=0; i < tmpEmail.length(); i++){
            if (allowStrInEmail.indexOf(tmpEmail.charAt(i)) > -1) 
                tmpEmailSanitized += tmpEmail.charAt(i);
        }
        
        if(tmpEmail.length() != tmpEmailSanitized.length()){
            return new String("Error: Email format is not okay. Allowed characters are: " + allowStrInEmail);
        }
            
        for (int i=0; i < tmpPassword.length(); i++){
            if (allowStrInPassword.indexOf(tmpPassword.charAt(i)) > -1) 
                tmpPasswordSanitized += tmpPassword.charAt(i);
        }
        if(tmpPassword.length() != tmpPasswordSanitized.length()){
            return new String("Error: Password format is not okay. Allowed characters are: " + allowStrInPassword);
        }

        for (int i=0; i < tmpFirstName.length(); i++){
            if (allowStrInFirstNameOrLastName.indexOf(tmpFirstName.charAt(i)) > -1) 
                tmpFirstNameSanitized += tmpFirstName.charAt(i);
        }

        if(tmpFirstName.length() != tmpFirstNameSanitized.length()){
            return new String("Error: First name format is not okay. Allowed characters are: " + allowStrInFirstNameOrLastName);
        }

        for (int i=0; i < tmpLastName.length(); i++){
            if (allowStrInFirstNameOrLastName.indexOf(tmpLastName.charAt(i)) > -1) 
                tmpLastNameSanitized += tmpLastName.charAt(i);
        }

        if(tmpLastName.length() != tmpLastNameSanitized.length()){
            return new String("Error: Last name format is not okay. Allowed characters are: " + allowStrInFirstNameOrLastName);
        }
        
        userToRegister.setEmail(tmpEmailSanitized);
        userToRegister.setFirstname(tmpFirstNameSanitized);
        userToRegister.setLastname(tmpLastNameSanitized);
        userToRegister.setPassword(tmpPasswordSanitized);


        String roleName = null;
        if (tmpRoleToRegister.equals("admin")){
            roleName = "ROLE_ADMIN";
        }
        if (tmpRoleToRegister.equals("editor")){
            roleName = "ROLE_EDITOR";
        }
        if (tmpRoleToRegister.equals("user")){
            roleName = "ROLE_USER";
        }
        if (Objects.equals(roleName, null)){
            return "";
        }

        userToRegister.setRole(roleName);
        return service.register(userToRegister);
    }
}
