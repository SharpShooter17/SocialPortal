package com.ujazdowski.SocialPortal.repository;

import com.ujazdowski.SocialPortal.model.tables.InvitationNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface InvitationNotificationsRepository extends JpaRepository<InvitationNotification, Long> {
    List<InvitationNotification> findAllByForUser(Long forUser);
}
