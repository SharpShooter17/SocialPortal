package com.ujazdowski.SocialPortal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @RequestMapping("/home")
    public ModelAndView index(){
        return new ModelAndView("home");
    }
}
