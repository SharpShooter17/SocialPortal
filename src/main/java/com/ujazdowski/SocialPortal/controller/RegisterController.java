package com.ujazdowski.SocialPortal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class RegisterController {
    @RequestMapping("/Register")
    public String register(){
        return "register";
    }
}