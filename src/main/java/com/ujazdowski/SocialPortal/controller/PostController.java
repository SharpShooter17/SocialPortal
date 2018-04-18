package com.ujazdowski.SocialPortal.controller;

import com.ujazdowski.SocialPortal.SocialPortalUtils;
import com.ujazdowski.SocialPortal.exceptions.PostNotFoundException;
import com.ujazdowski.SocialPortal.exceptions.UnauthorizedAcctionException;
import com.ujazdowski.SocialPortal.model.forms.PostForm;
import com.ujazdowski.SocialPortal.model.tables.Comment;
import com.ujazdowski.SocialPortal.model.tables.Post;
import com.ujazdowski.SocialPortal.model.tables.User;
import com.ujazdowski.SocialPortal.repository.CommentsRepository;
import com.ujazdowski.SocialPortal.repository.PostsRepository;
import com.ujazdowski.SocialPortal.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;

@RequestMapping(value = "/home/post")
@Controller
public class PostController {
    private static Logger logger = LoggerFactory.getLogger(PostsRepository.class);
    private final PostsRepository postsRepository;
    private final CommentsRepository commentsRepository;
    private final UsersRepository usersRepository;

    public PostController(PostsRepository postsRepository, CommentsRepository commentsRepository, UsersRepository usersRepository) {
        this.postsRepository = postsRepository;
        this.commentsRepository = commentsRepository;
        this.usersRepository = usersRepository;
    }

    @RequestMapping(value = "/{postId}", method = RequestMethod.GET)
    public ModelAndView post(@PathVariable("postId")Long postId, Model model) throws PostNotFoundException {
        Post post = this.postsRepository.findOne(postId);
        if (!(post instanceof Post)){
            throw new PostNotFoundException();
        }
        return new ModelAndView("post", "post", post);
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public ModelAndView newPost(@ModelAttribute("newPost") PostForm post, BindingResult binder, Model model) throws UnauthorizedAcctionException {
        User logged = SocialPortalUtils.getLoggedUser();
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

    @RequestMapping(value = {"/delete/{postId}"}, method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("postId") Long postId){
        Post post = this.postsRepository.findOne(postId);

        Long userId = post.getUser().getUserId();

        for (Comment c: post.getComments()) {
            this.commentsRepository.delete(c);
        }

        this.postsRepository.delete(post);
        return new ModelAndView(new RedirectView( "/home/profile/" + userId ));
    }
}
