package com.ujazdowski.SocialPortal.controller;

import com.ujazdowski.SocialPortal.model.tables.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Map;

@Controller
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
    @RequestMapping(value={"/login"}, method = RequestMethod.GET)
    public ModelAndView login(Model model){
        User user = (User)model.asMap().get("user");
        if (user == null){
            user = new User();
        }

        return new ModelAndView("login", "user", user);
    }
}
