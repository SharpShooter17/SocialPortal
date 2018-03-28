package com.ujazdowski.SocialPortal.model.tables;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "INVITATION_NOTIFICATION_T")
public class InvitationNotification {
    @NotNull
    @Id
    @Column(name = "INVITATION_NOTIFICATION_ID")
    private Long notificationId;

    @NotNull
    @Column(name = "FOR_USER_ID")
    private Long forUser;

    @NotNull
    @OneToOne
    @JoinColumn(name = "INVITATION_ID")
    private Invitation invitation;
}
