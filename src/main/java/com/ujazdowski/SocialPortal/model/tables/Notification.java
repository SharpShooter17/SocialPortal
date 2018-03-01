package com.ujazdowski.SocialPortal.model.tables;

import com.ujazdowski.SocialPortal.model.enums.NOTIFICATION;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "NOTIFICATION_T")
public class Notification {
    @NotNull
    @Id
    @Column(name = "NOTIFICATION_ID")
    private Long notificationId;

    @NotNull
    @OneToOne
    @JoinColumn(name = "FOR_USER_ID")
    private User forUser;

    @NotNull
    @OneToOne
    @JoinColumn(name = "TYPE_OF_NOTIFICATION_ID")
    private TypeOfNotification type;

    @NotNull
    @Column(name = "REFERENCE_ID")
    private Long refId;

}
