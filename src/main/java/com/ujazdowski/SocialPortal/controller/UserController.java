package com.ujazdowski.SocialPortal.controller;

import com.ujazdowski.SocialPortal.model.tables.User;
import com.ujazdowski.SocialPortal.repository.UsersRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import org.apache.log4j.Logger;

@RequestMapping("/secured")
@RestController
public class UserController {
    private static org.apache.log4j.Logger logger = Logger.getLogger(UserController.class);
    private UsersRepository usersRepository;

    UserController(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public List<User> getUsers(){
        return usersRepository.findAll();
    }

}
