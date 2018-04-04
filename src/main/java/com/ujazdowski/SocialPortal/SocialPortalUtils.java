package com.ujazdowski.SocialPortal;

import com.ujazdowski.SocialPortal.model.tables.User;
import com.ujazdowski.SocialPortal.service.CustomUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class SocialPortalUtils {
    public static User getLoggedUser(){
        User logged = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        return logged;
    }
}
