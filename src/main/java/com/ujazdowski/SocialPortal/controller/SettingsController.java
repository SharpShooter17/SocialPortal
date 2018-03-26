package com.ujazdowski.SocialPortal.controller;

import com.ujazdowski.SocialPortal.exceptions.UserNotExistsException;
import com.ujazdowski.SocialPortal.model.forms.SettingsForm;
import com.ujazdowski.SocialPortal.model.tables.User;
import com.ujazdowski.SocialPortal.repository.LanguagesRepository;
import com.ujazdowski.SocialPortal.repository.UsersRepository;
import com.ujazdowski.SocialPortal.service.CustomUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(value = "/home/settings")
public class SettingsController {
    private final Logger logger = LoggerFactory.getLogger(SettingsController.class);
    private final UsersRepository usersRepository;
    private final LanguagesRepository languagesRepository;

    public SettingsController(UsersRepository usersRepository, LanguagesRepository languagesRepository){
        this.usersRepository = usersRepository;
        this.languagesRepository = languagesRepository;
    }

    private User getLoggedUser() throws UserNotExistsException {
        User userPrincipal = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        Optional<User> oUser = this.usersRepository.findUserByEmail(userPrincipal.getEmail());
        oUser.orElseThrow(UserNotExistsException::new);
        return oUser.get();
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView index() throws UserNotExistsException {
        User user = this.getLoggedUser();

        SettingsForm settingsForm = new SettingsForm();
        settingsForm.setFirstName(user.getFirstName());
        settingsForm.setSecondName(user.getSecondName());
        settingsForm.setLanguage(user.getLanguage());

        ModelAndView mv = new ModelAndView("settings");
        mv.addObject("languages", this.languagesRepository.findAll());
        mv.addObject("userSettings", settingsForm);
        return mv;
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public ModelAndView saveChanges(@ModelAttribute("userSettings") SettingsForm userSettings,
                                    BindingResult result,
                                    Model model) throws UserNotExistsException {
        if (result.hasErrors()) {
            for (ObjectError error: result.getAllErrors()) {
                logger.warn("ERROR {}", error.getDefaultMessage());
            }
            return index();
        }

        User user = this.getLoggedUser();

        user.setFirstName(userSettings.getFirstName());
        user.setSecondName(userSettings.getSecondName());
        user.setLanguage(userSettings.getLanguage());

        this.usersRepository.save(user);
        return new ModelAndView("redirect:/home");
    }

}
