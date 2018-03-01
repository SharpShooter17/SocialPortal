package com.ujazdowski.SocialPortal.repository;

import com.ujazdowski.SocialPortal.model.tables.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface InvitationsRepository extends JpaRepository<Invitation, Long> {
}
