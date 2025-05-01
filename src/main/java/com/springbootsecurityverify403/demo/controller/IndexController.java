package com.springbootsecurityverify403.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class IndexController {
    
    @GetMapping("/")
    public String getIndex() {
        return String.format("""
            <!doctype html><html>
                <head>
                    <meta charset="UTF-8"/>
                    <style> div { margin: 20px; font-size: 18px; } </style>
                </head>
                <body>
                    <div>Index page </div>
                    <div><a href="%s">Register</a> </div>
                    <div><a href="%s">Login</a> </div>
                    <div>
                        <div>If you are logged out or you are not logged in, </div>
                        <div>you might try on this page: POST /auth/login</div>
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
                """,
            "/userRegister",
            "/login");
    }
    
}
