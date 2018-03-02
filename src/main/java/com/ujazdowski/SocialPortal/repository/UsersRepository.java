package com.ujazdowski.SocialPortal.repository;

import com.ujazdowski.SocialPortal.model.tables.User;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UsersRepository extends JpaRepository<User, Long> {
    User findUserByEmail(@Email String email);
}
