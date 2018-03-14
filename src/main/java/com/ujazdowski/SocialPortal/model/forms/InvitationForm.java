package com.ujazdowski.SocialPortal.model.forms;

import lombok.Data;

@Data
public class InvitationForm {
    private Long toUser;
    private Long fromUser;
}
