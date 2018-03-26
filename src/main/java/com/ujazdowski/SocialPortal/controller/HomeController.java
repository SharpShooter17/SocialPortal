package com.ujazdowski.SocialPortal.controller;

import com.ujazdowski.SocialPortal.exceptions.UserNotExistsException;
import com.ujazdowski.SocialPortal.model.tables.Post;
import com.ujazdowski.SocialPortal.model.tables.User;
import com.ujazdowski.SocialPortal.repository.PostsRepository;
import com.ujazdowski.SocialPortal.repository.UsersRepository;
import com.ujazdowski.SocialPortal.service.CustomUser;
import com.ujazdowski.SocialPortal.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final UsersRepository usersRepository;
    private final PostService postService;

    private Logger logger = LoggerFactory.getLogger(HomeController.class);

    public HomeController(UsersRepository usersRepository, PostService postService) {
        this.usersRepository = usersRepository;
        this.postService = postService;
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView page(Model model) throws UserNotExistsException {
        return index(0, model);
    }

    @RequestMapping(value = {"/{page}"}, method = RequestMethod.GET)
    public ModelAndView index(@PathVariable("page") Integer page, Model model) throws UserNotExistsException {
        User user = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

        Optional<com.ujazdowski.SocialPortal.model.tables.User> oUser = this.usersRepository.findUserByEmail(user.getEmail());
        oUser.orElseThrow(()-> new UserNotExistsException());

        Page<Post> posts = this.postService.getUserFriendsPosts(oUser.get(), new PageRequest(page,5));
        ModelAndView mv = new ModelAndView("home");

        mv.addObject("user", oUser.get());
        mv.addObject("posts", posts);
        mv.addObject("totalPages", posts.getTotalPages());
        mv.addObject("onPage", posts.getNumber());

        return mv;
    }
}
