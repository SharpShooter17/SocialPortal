package com.ujazdowski.SocialPortal.controller;

import com.ujazdowski.SocialPortal.exceptions.UnauthorizedAcctionException;
import com.ujazdowski.SocialPortal.model.forms.PostForm;
import com.ujazdowski.SocialPortal.model.tables.Post;
import com.ujazdowski.SocialPortal.model.tables.User;
import com.ujazdowski.SocialPortal.repository.PostsRepository;
import com.ujazdowski.SocialPortal.repository.UsersRepository;
import com.ujazdowski.SocialPortal.service.CustomUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@RequestMapping(value = "/home/post")
@Controller
public class PostController {
    private final PostsRepository postsRepository;
    private final UsersRepository usersRepository;

    public PostController(PostsRepository postsRepository, UsersRepository usersRepository) {
        this.postsRepository = postsRepository;
        this.usersRepository = usersRepository;
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public ModelAndView newPost(@ModelAttribute("newPost") PostForm post, BindingResult binder, Model model) throws UnauthorizedAcctionException {
        User logged = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        if (logged.getUserId() != post.getUserId()){
            throw new UnauthorizedAcctionException();
        }

        Post p = new Post();
        p.setUser(this.usersRepository.findByUserId(post.getUserId()).get());
        p.setText(post.getText());
        p.setDate(new Date());

        postsRepository.save(p);
        return new ModelAndView("redirect:/home/profile/" + post.getUserId());
    }
}
