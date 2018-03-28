package com.ujazdowski.SocialPortal.service;

import com.ujazdowski.SocialPortal.exceptions.UserNotExistsException;
import com.ujazdowski.SocialPortal.model.tables.InvitationNotification;
import com.ujazdowski.SocialPortal.model.tables.User;
import com.ujazdowski.SocialPortal.repository.InvitationNotificationsRepository;
import com.ujazdowski.SocialPortal.repository.UsersRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    private final InvitationNotificationsRepository invitationNotificationsRepository;
    private final UsersRepository usersRepository;

    public NotificationService(InvitationNotificationsRepository invitationNotificationsRepository, UsersRepository usersRepository) {
        this.invitationNotificationsRepository = invitationNotificationsRepository;
        this.usersRepository = usersRepository;
    }

    public List<InvitationNotification> getInvitationNotifications() throws UserNotExistsException {
        User user = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        Optional<User> oUser = this.usersRepository.findUserByEmail(user.getEmail());
        oUser.orElseThrow(()-> new UserNotExistsException());
        return this.invitationNotificationsRepository.findAllByForUser(oUser.get().getUserId());
    }
}
