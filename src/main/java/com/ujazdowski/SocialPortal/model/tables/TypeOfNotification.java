package com.ujazdowski.SocialPortal.model.tables;

import com.ujazdowski.SocialPortal.model.enums.NOTIFICATION;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "TYPE_OF_NOTIFICATION_T")
public class TypeOfNotification {

    @NotNull
    @Id
    @Column(name = "TYPE_OF_NOTIFICATION_ID")
    private Long typeOfNotificationId;

    @NotNull
    @Column(name = "TYPE")
    private NOTIFICATION type;
}
