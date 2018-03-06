package com.ujazdowski.SocialPortal.controller;

import com.ujazdowski.SocialPortal.model.tables.Language;
import com.ujazdowski.SocialPortal.model.tables.User;
import com.ujazdowski.SocialPortal.model.tables.UserRole;
import com.ujazdowski.SocialPortal.repository.UsersRepository;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.sql.Timestamp;

@Controller
public class RegisterController {
    private static Logger logger = Logger.getLogger(RegisterController.class);
    private UsersRepository usersRepository;

    public RegisterController(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @RequestMapping(value = "/Register", method = RequestMethod.GET)
    public ModelAndView register(){
        User user = new User();
        user.setLastTimeOnline(new Timestamp(0));
        user.setProfilePhotoId(null);
        UserRole role = new UserRole();
        role.setUserRoleId(1L);
        user.setRole(role);

        return new ModelAndView("register", "user", user);
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@Valid @ModelAttribute("user")User user, BindingResult result, ModelMap model){
        if (result.hasErrors()) {
            logger.error(new String("adduser error") );
            for (ObjectError error: result.getAllErrors()) {
                logger.error(error.getDefaultMessage());
            }
        }

        UserRole role = new UserRole();
        role.setUserRoleId(1L);
        user.setRole(role);

        String hashPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword( hashPassword );
        usersRepository.save(user);
        return "login";
    }
}
