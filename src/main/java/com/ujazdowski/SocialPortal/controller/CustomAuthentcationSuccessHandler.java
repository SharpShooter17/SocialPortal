package com.ujazdowski.SocialPortal.controller;

import com.ujazdowski.SocialPortal.SocialPortalUtils;
import com.ujazdowski.SocialPortal.model.tables.User;
import com.ujazdowski.SocialPortal.repository.UsersRepository;
import com.ujazdowski.SocialPortal.service.CustomUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class CustomAuthentcationSuccessHandler implements AuthenticationSuccessHandler{

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private final UsersRepository usersRepository;
    private final LocaleResolver localeResolver;

    public CustomAuthentcationSuccessHandler(UsersRepository usersRepository, LocaleResolver localeResolver) {
        super();
        this.usersRepository = usersRepository;
        this.localeResolver = localeResolver;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User user = SocialPortalUtils.getLoggedUser();
        Optional<User> oUser = this.usersRepository.findUserByEmail(user.getEmail());
        localeResolver.setLocale(request, response, StringUtils.parseLocaleString(oUser.get().getLanguage().getCode()));
        redirectStrategy.sendRedirect(request, response, "/home");
    }
}