package com.ujazdowski.SocialPortal.repository;

import com.ujazdowski.SocialPortal.model.tables.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UsersRolesRepository extends JpaRepository<UserRole, Long> {
}
