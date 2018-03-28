package com.ujazdowski.SocialPortal.model.Notification;

import com.ujazdowski.SocialPortal.model.enums.NOTIFICATION;
import lombok.Data;

@Data
public abstract class AbstractNotification {
    private NOTIFICATION type;
    private Long id;
}
