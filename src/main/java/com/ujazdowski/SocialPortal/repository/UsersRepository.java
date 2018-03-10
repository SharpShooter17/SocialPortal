package com.ujazdowski.SocialPortal.repository;

import com.ujazdowski.SocialPortal.model.tables.User;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(@Email String email);
}
