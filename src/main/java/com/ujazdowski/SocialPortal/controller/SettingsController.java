package com.ujazdowski.SocialPortal.controller;

import com.ujazdowski.SocialPortal.SocialPortalUtils;
import com.ujazdowski.SocialPortal.exceptions.UserNotExistsException;
import com.ujazdowski.SocialPortal.model.forms.SettingsForm;
import com.ujazdowski.SocialPortal.model.tables.User;
import com.ujazdowski.SocialPortal.repository.LanguagesRepository;
import com.ujazdowski.SocialPortal.repository.PictruesRepository;
import com.ujazdowski.SocialPortal.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/home/settings")
public class SettingsController {
    private final Logger logger = LoggerFactory.getLogger(SettingsController.class);
    private final UsersRepository usersRepository;
    private final LanguagesRepository languagesRepository;
    private final PictruesRepository pictruesRepository;

    public SettingsController(UsersRepository usersRepository, LanguagesRepository languagesRepository, PictruesRepository pictruesRepository){
        this.usersRepository = usersRepository;
        this.languagesRepository = languagesRepository;
        this.pictruesRepository = pictruesRepository;
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView index() throws UserNotExistsException {
        User user = SocialPortalUtils.getLoggedUser();

        SettingsForm settingsForm = new SettingsForm();
        settingsForm.setFirstName(user.getFirstName());
        settingsForm.setSecondName(user.getSecondName());
        settingsForm.setLanguage(user.getLanguage());
        settingsForm.setProfilePhoto(user.getProfile());

        ModelAndView mv = new ModelAndView("settings");
        mv.addObject("languages", this.languagesRepository.findAll());
        mv.addObject("userSettings", settingsForm);
        mv.addObject("pictrues", this.pictruesRepository.getPictrueIdsByUser_UserId(user.getUserId()));
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

        User user = SocialPortalUtils.getLoggedUser();

        user.setFirstName(userSettings.getFirstName());
        user.setSecondName(userSettings.getSecondName());
        user.setLanguage(userSettings.getLanguage());
        user.setProfile(userSettings.getProfilePhoto());
        
        this.usersRepository.save(user);
        return new ModelAndView("redirect:/home");
    }

}
