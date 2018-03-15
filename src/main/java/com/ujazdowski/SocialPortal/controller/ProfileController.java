package com.ujazdowski.SocialPortal.controller;

import com.ujazdowski.SocialPortal.exceptions.UserNotExistsException;
import com.ujazdowski.SocialPortal.model.forms.InvitationForm;
import com.ujazdowski.SocialPortal.model.tables.Invitation;
import com.ujazdowski.SocialPortal.model.tables.User;
import com.ujazdowski.SocialPortal.repository.InvitationsRepository;
import com.ujazdowski.SocialPortal.repository.PostsRepository;
import com.ujazdowski.SocialPortal.repository.UsersRepository;
import com.ujazdowski.SocialPortal.service.CustomUser;
import com.ujazdowski.SocialPortal.service.InvitationsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.Optional;

@RequestMapping("/home/profile")
@Controller
@ComponentScan(basePackageClasses = {
                        com.ujazdowski.SocialPortal.service.InvitationsService.class,
                        com.ujazdowski.SocialPortal.repository.InvitationsRepository.class})
public class ProfileController {
    private static Logger logger = LoggerFactory.getLogger(ProfileController.class);
    private final UsersRepository usersRepository;
    private final InvitationsService invitationsService;
    private final InvitationsRepository invitationsRepository;
    private final PostsRepository postsRepository;

    public ProfileController(InvitationsService invitationsService, UsersRepository usersRepository, InvitationsRepository invitationsRepository, PostsRepository postsRepository){
        this.usersRepository = usersRepository;
        this.invitationsService = invitationsService;
        this.invitationsRepository = invitationsRepository;
        this.postsRepository = postsRepository;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ModelAndView profile(@PathVariable("userId") Long userId, Model model) throws Exception {
        Optional<User> oUser = this.usersRepository.findByUserId(userId);

        logger.info("USERID {}", userId);
        logger.info("USER {}", oUser.get().getEmail());

        oUser.orElseThrow(() -> new UserNotExistsException());
        User user = oUser.get();
        User logged = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        user.setPassword(null);
        logged.setPassword(null);

        ModelAndView mv = new ModelAndView("profile");
        mv.addObject("user", user);
        mv.addObject("areFriends", this.invitationsService.usersAreFriends(user, logged));
        mv.addObject("invitationForm", new InvitationForm());
        mv.addObject("friends", this.invitationsService.getFriends(userId));
        mv.addObject("posts", this.postsRepository.findAllByUser(user));

        return mv;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.POST)
    public ModelAndView invite(@PathVariable("userId") Long userId,
            @ModelAttribute("invitationForm")InvitationForm invitationForm,
                         BindingResult result,
                         Model model) throws Exception {
        if (result.hasErrors()) {
            for (ObjectError error: result.getAllErrors()) {
                logger.warn("ERROR {}", error.getDefaultMessage());
            }
            return profile(userId, model);
        }
        Optional<User> oUserFrom = this.usersRepository.findByUserId(invitationForm.getFromUser());
        Optional<User> oUserTo = this.usersRepository.findByUserId(invitationForm.getToUser());

        oUserFrom.orElseThrow(()->new UserNotExistsException());
        oUserTo.orElseThrow(()->new UserNotExistsException());

        Invitation i = this.invitationsService.usersAreFriends(oUserFrom.get(), oUserTo.get());
        if (i.getSended() == null){
            i.setFromUser(oUserFrom.get());
            i.setToUser(oUserTo.get());
            i.setSended( new Timestamp(0) );
        }
        i.setAccepted(invitationForm.getAccepted());
        this.invitationsRepository.save(i);

        return profile(userId, model);
    }
}
