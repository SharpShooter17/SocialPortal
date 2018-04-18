package com.ujazdowski.SocialPortal.controller;

import com.ujazdowski.SocialPortal.model.tables.User;
import com.ujazdowski.SocialPortal.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/home/search")
public class SearchController {
    private static Logger logger = LoggerFactory.getLogger(SearchController.class);
    private final UsersRepository usersRepository;

    public SearchController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @RequestMapping(value = {"/", ""}, params = "search", method = RequestMethod.GET)
    public ModelAndView searchUser(@RequestParam("search") String value, Model model){
        logger.info("Search: {}", value);
        Optional<List<User>> oUsers = this.usersRepository.search(value);
        List<User> results;

        if (oUsers.isPresent()){
            results = oUsers.get();
        } else {
            results = new ArrayList<>();
        }

        ModelAndView mv = new ModelAndView("search");
        mv.addObject("results", results);

        return mv;
    }

}
