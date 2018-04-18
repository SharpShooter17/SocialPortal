package com.ujazdowski.SocialPortal.controller;

import com.ujazdowski.SocialPortal.exceptions.GenericError;
import com.ujazdowski.SocialPortal.exceptions.UserNotExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@ControllerAdvice
public class ExceptionController {

    private static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @ExceptionHandler(GenericError.class)
    public ModelAndView habdleError(GenericError ex){
        ModelAndView mv = new ModelAndView("error");

        mv.addObject("timestamp", ex.getDate());
        mv.addObject("status", ex.getStatus());
        mv.addObject("error", ex.getError());
        mv.addObject("message", ex.getMessage());
        mv.addObject("exception", ex.getException());

        return mv;
    }

}
