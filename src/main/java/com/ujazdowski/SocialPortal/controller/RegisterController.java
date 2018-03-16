package com.ujazdowski.SocialPortal.controller;

import com.ujazdowski.SocialPortal.exceptions.UserExistsException;
import com.ujazdowski.SocialPortal.model.tables.Role;
import com.ujazdowski.SocialPortal.model.tables.User;
import com.ujazdowski.SocialPortal.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.sql.Timestamp;

@Controller
public class RegisterController {
    private static Logger logger = Logger.getLogger(RegisterController.class);
    private final UserService userService;

    public RegisterController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/Register", method = RequestMethod.GET)
    public ModelAndView register(){
        User user = new User();
        user.setLastTimeOnline(new Timestamp(0));
        user.setProfile(null);
        Role role = new Role();
        role.setUserRoleId(1L);
        user.getRoles().add(role);

        return new ModelAndView("register", "user", user);
    }

    @RequestMapping(value = "/Register", method = RequestMethod.POST)
    public ModelAndView addUser(@Valid @ModelAttribute("user")User user, BindingResult result, RedirectAttributes model) throws UserExistsException {
        if (result.hasErrors()) {
            logger.error(new String("adduser error") );
            for (ObjectError error: result.getAllErrors()) {
                logger.error(error.getDefaultMessage());
            }
            return new ModelAndView("register", result.getModel());
        }

        userService.addNewUser(user);
        user.setPassword("");
        model.addFlashAttribute("user", user);

        return new ModelAndView("redirect:/login");
    }
}
