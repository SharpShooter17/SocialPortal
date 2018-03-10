package com.ujazdowski.SocialPortal.controller;

import com.ujazdowski.SocialPortal.exceptions.UserNotExistsException;
import com.ujazdowski.SocialPortal.model.tables.User;
import com.ujazdowski.SocialPortal.repository.UsersRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/home/profile")
public class Profilecontroller {
    private final UsersRepository usersRepository;

    public Profilecontroller(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ModelAndView profile(@PathVariable("userId") Long userId, Model model) throws UserNotExistsException{
        Optional<User> oUser = this.usersRepository.findByUserId(userId);
        oUser.orElseThrow(() -> new UserNotExistsException());
        User user = oUser.get();

        return new ModelAndView("profile", "user", user);
    }
}
