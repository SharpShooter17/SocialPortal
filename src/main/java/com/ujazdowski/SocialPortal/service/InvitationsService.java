package com.ujazdowski.SocialPortal.service;

import com.ujazdowski.SocialPortal.model.tables.Invitation;
import com.ujazdowski.SocialPortal.model.tables.User;
import com.ujazdowski.SocialPortal.repository.InvitationsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class InvitationsService {
    private final InvitationsRepository invitationsRepository;
    private final static Logger logger = LoggerFactory.getLogger(InvitationsService.class);
    public InvitationsService(InvitationsRepository invitationsRepository) {
        this.invitationsRepository = invitationsRepository;
    }

    public Invitation usersAreFriends(User u1, User u2) throws Exception {
        logger.info("Are friends: " + u1.getEmail());
        logger.info("Are friends: " + u2.getEmail());

        List<Invitation> list = this.invitationsRepository.usersAreFriends(u1.getUserId(), u2.getUserId());
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

    public void invite(Invitation invitation){
        invitation.setSended(new Timestamp(new Date().getTime()));
        invitation.setAccepted(false);
        this.invitationsRepository.save(invitation);
    }
}
