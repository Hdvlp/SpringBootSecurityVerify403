package com.springbootsecurityverify403.demo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class RegisterController {

    // Anyone can register to become a user
    // in this case.
    //
    // Permissions are set in the file SecurityConfig.
    
    @GetMapping("/userRegister")
    public String indexPage(HttpServletRequest request) {

        return new String("""
                <!doctype>
                <html><head><meta charset="UTF-8"/>
                <style>body{font-size: 24px;} input{font-size: 24px;} .box{margin: 24px;}</style>
                </head>
                <body>
                <div class="box">
                    <div>Register As User</div>
                    <div><form action="/register" method="POST"></div>
                    <div>Email: <input name="email" type="text" value="user@example.com"/></div>
                    <div>First name: <input name="firstname" type="text" value="user"/></div>
                    <div>Last name: <input name="lastname" type="text" value="user"/></div>
                    <div>Password: <input name="password" type="password"/></div>
                    <input name="role" type="hidden" value="user"/>
                    <div><input name="submit" type="submit" value="Submit"/></div>
                    <div></form></div>
                </div>

                <div class="box">
                    <div>Register As Editor</div>
                    <div><form action="/register" method="POST"></div>
                    <div>Email: <input name="email" type="text" value="editor@example.com"/></div>
                    <div>First name: <input name="firstname" type="text" value="editor"/></div>
                    <div>Last name: <input name="lastname" type="text" value="editor"/></div>
                    <div>Password: <input name="password" type="password"/></div>
                    <input name="role" type="hidden" value="editor"/>
                    <div><input name="submit" type="submit" value="Submit"/></div>
                    <div></form></div>
                </div>
                
                <div class="box">
                    <div>Register As Admin</div>
                    <div><form action="/register" method="POST"></div>
                    <div>Email: <input name="email" type="text" value="admin@example.com"/></div>
                    <div>First name: <input name="firstname" type="text" value="admin"/></div>
                    <div>Last name: <input name="lastname" type="text" value="admin"/></div>
                    <div>Password: <input name="password" type="password"/></div>
                    <input name="role" type="hidden" value="admin"/>
                    <div><input name="submit" type="submit" value="Submit"/></div>
                    <div></form></div>
                </div>
                </body>
                </html>
                """);
    }

}

