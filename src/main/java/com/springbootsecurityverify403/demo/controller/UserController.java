package com.springbootsecurityverify403.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    
    @GetMapping("/user")
    public String getUserPage() {
        return new String("""
            <!doctype html><html>
                <head>
                    <meta charset="UTF-8"/>
                    <style> div { margin: 20px; font-size: 18px; } </style>
                </head>
                <body>
                    <div>User page <a href="/logout">Log out</a></div>
                    <div>
                        <div>Simulation: /auth/login</div>
                        <form action="/auth/login" method="POST">
                        <div>Some data: <input type="text" name="data" value="some data"/></div>
                        <div><input type="submit" value="POST /auth/login"/></div>
                        </form>
                    </div>
                    <div>
                        <a href="/setStatus?status=500">Make the service to return HTTP status code 500</a>
                    </div>
                    <div>
                        <a href="/setStatus?status=200">Make the service to return HTTP status code 200</a>
                    </div>
                </body>
            </html>
                """);
    }
    
}