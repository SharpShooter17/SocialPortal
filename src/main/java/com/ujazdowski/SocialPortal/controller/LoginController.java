package com.ujazdowski.SocialPortal.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.Map;

@Controller
public class LoginController {
    @Value("${application.homePage.welcome:Hello World}")
    private String welcome;

    @RequestMapping(value={"/"}, method = RequestMethod.GET)
    public String homePage(Map<String, Object> model){
        model.put("time", new Date());
        model.put("message", this.welcome);
        return "login";
    }
}
