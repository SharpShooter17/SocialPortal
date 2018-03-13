package com.ujazdowski.SocialPortal.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUser extends User{
    @Getter @Setter
    private com.ujazdowski.SocialPortal.model.tables.User user;

    public CustomUser(Collection<? extends GrantedAuthority> authorities, com.ujazdowski.SocialPortal.model.tables.User user) {
        super(user.getEmail(), user.getPassword(), authorities);
        this.user = user;
    }
}
