package com.ujazdowski.SocialPortal.service;


import com.ujazdowski.SocialPortal.model.tables.Role;
import com.ujazdowski.SocialPortal.model.tables.User;
import com.ujazdowski.SocialPortal.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDerailsService implements UserDetailsService {
    private final UsersRepository usersRepository;
    private static Logger logger = LoggerFactory.getLogger(CustomUserDerailsService.class);

    public CustomUserDerailsService(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> oUser = usersRepository.findUserByEmail(email);
        oUser.orElseThrow(() -> new UsernameNotFoundException(email + " not exist in database"));
        List<GrantedAuthority> auths = new ArrayList<>();

        for (Role r : oUser.get().getRoles()) {
            auths.add(new SimpleGrantedAuthority("ROLE_" + r.getRole()));
        }

        return (UserDetails) new CustomUser(auths, oUser.get());
    }
}
