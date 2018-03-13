package com.ujazdowski.SocialPortal.controller;

import com.ujazdowski.SocialPortal.exceptions.UserNotExistsException;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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

        ModelAndView mv = new ModelAndView("profile");
        mv.addObject("user", user);
        mv.addObject("friends", this.invitationsService.usersAreFriends(user, logged));

        return mv;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.POST)
    public ModelAndView invite(@PathVariable("userId") Long userId,
                         @Valid @ModelAttribute("friends")Invitation invitation,
                         BindingResult result,
                         Model model) throws Exception {
        if (result.hasErrors()) {
            logger.warn("bledy... ");
        }
        User user = invitation.getFromUser();
        logger.info("FROM USER: "  + user.getUserId());
        this.invitationsService.addNewUser(invitation);
        return new ModelAndView("home");
        // return profile(userId, model);
    }
}
