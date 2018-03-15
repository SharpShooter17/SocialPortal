package com.ujazdowski.SocialPortal.service;

import com.ujazdowski.SocialPortal.model.tables.Invitation;
import com.ujazdowski.SocialPortal.model.tables.User;
import com.ujazdowski.SocialPortal.repository.InvitationsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@ComponentScan(basePackageClasses = {com.ujazdowski.SocialPortal.repository.InvitationsRepository.class})
public class InvitationsService {
    private final InvitationsRepository invitationsRepository;
    private final static Logger logger = LoggerFactory.getLogger(InvitationsService.class);
    public InvitationsService(InvitationsRepository invitationsRepository) {
        this.invitationsRepository = invitationsRepository;
    }

    public Invitation usersAreFriends(Long u1, Long u2) throws Exception {
        List<Invitation> list = this.invitationsRepository.usersAreFriends(u1, u2);
        if (list.size() > 1){
            logger.warn("Too much size of list");
            throw new Exception("This situation is not possible!");
        }
        if (list.size() == 0){
            return new Invitation();
        } else {
            return list.get(0);
        }
    }

    public Invitation usersAreFriends(User u1, User u2) throws Exception {
        return usersAreFriends(u1.getUserId(), u2.getUserId());
    }

    public List<User> getFriends(Long id){
        List<Invitation> invitations = this.invitationsRepository.findAllFriends(id);
        List<User> friends = new ArrayList<>();
        for (Invitation i : invitations){
            if (i.getFromUser().getUserId() == id){
                friends.add(i.getToUser());
            } else {
                friends.add(i.getFromUser());
            }
        }
        return friends;
    }
}
