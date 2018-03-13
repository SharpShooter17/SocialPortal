package com.ujazdowski.SocialPortal.model.tables;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "INVITATION_T")
public class Invitation {
    @NotNull
    @Id
    @GeneratedValue
    @Column(name = "INVITATION_ID")
    private Long invitationId;

    @NotNull
    @OneToOne
    @JoinColumn(name = "FROM_USER_ID")
    private User fromUser;

    @NotNull
    @OneToOne
    @JoinColumn(name = "TO_USER_ID")
    private User toUser;

    @NotNull
    @Column(name = "SENDED")
    private Timestamp sended;

    @NotNull
    @Column(name = "ACCEPTED")
    private Boolean accepted;
}
