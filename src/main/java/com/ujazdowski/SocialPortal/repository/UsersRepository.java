package com.ujazdowski.SocialPortal.repository;

import com.ujazdowski.SocialPortal.model.tables.User;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(@Email String email);
    Optional<User> findByUserId(Long id);

    @Query(value = "SELECT u FROM User u WHERE CONCAT(u.firstName, '%', u.secondName) LIKE (CONCAT('%', FUNCTION('REPLACE', :value, ' ', '%'),'%')) OR (u.email LIKE CONCAT('%', :value, '%'))")
    Optional<List<User>> search( @Param("value") String value);
}
