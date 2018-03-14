package com.ujazdowski.SocialPortal.controller;

import com.ujazdowski.SocialPortal.exceptions.UserNotExistsException;
import com.ujazdowski.SocialPortal.model.forms.InvitationForm;
import com.ujazdowski.SocialPortal.model.tables.Invitation;
import com.ujazdowski.SocialPortal.model.tables.User;
import com.ujazdowski.SocialPortal.repository.UsersRepository;
import com.ujazdowski.SocialPortal.service.CustomUser;
import com.ujazdowski.SocialPortal.service.InvitationsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.text.html.Option;
import java.util.Optional;

@RequestMapping("/home/profile")
@Controller
public class ProfileController {
    private static Logger logger = LoggerFactory.getLogger(ProfileController.class);
    private final UsersRepository usersRepository;
    private final InvitationsService invitationsService;

    public ProfileController(UsersRepository usersRepository, InvitationsService invitationsService){
        this.usersRepository = usersRepository;
        this.invitationsService = invitationsService;
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
        mv.addObject("friends", this.invitationsService.usersAreFriends(user, logged));
        mv.addObject("invitationForm", new InvitationForm());

        return mv;
    }

    @RequestMapping(value = "/invite", method = RequestMethod.POST)
    public ModelAndView invite(@ModelAttribute("invitationForm")InvitationForm invitationForm,
                         BindingResult result,
                         Model model) throws Exception {
        if (result.hasErrors()) {
            for (ObjectError error: result.getAllErrors()) {
                logger.warn("ERROR {}", error.getDefaultMessage());
            }
            return new ModelAndView("home");
        }
        Optional<User> oUserFrom = this.usersRepository.findByUserId(invitationForm.getFromUser());
        Optional<User> oUserTo = this.usersRepository.findByUserId(invitationForm.getToUser());

        oUserFrom.orElseThrow(()->new UserNotExistsException());
        oUserTo.orElseThrow(()->new UserNotExistsException());

        Invitation invitation = new Invitation();
        invitation.setFromUser(oUserFrom.get());
        invitation.setToUser(oUserTo.get());

        this.invitationsService.invite(invitation);

        return new ModelAndView("home");
    }
}
