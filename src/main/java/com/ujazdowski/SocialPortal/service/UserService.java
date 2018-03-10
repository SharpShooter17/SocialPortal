package com.ujazdowski.SocialPortal.service;

import com.ujazdowski.SocialPortal.exceptions.UserExistsException;
import com.ujazdowski.SocialPortal.model.tables.Role;
import com.ujazdowski.SocialPortal.model.tables.User;
import com.ujazdowski.SocialPortal.repository.UsersRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    public void addNewUser(User user) throws UserExistsException {
        Optional<User> oUser = usersRepository.findUserByEmail(user.getEmail());
        if (oUser.isPresent()) {
            throw new UserExistsException();
        }

        Role role = new Role();
        role.setUserRoleId(1L);
        user.getRoles().add(role);

        String hashPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword( hashPassword );

        usersRepository.save(user);
    }
}
