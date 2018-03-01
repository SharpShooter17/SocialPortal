package com.ujazdowski.SocialPortal.model.tables;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "MESSAGE_T")
public class Message {
    @NotNull
    @Id
    @Column(name = "MESSAGE_ID")
    private Long messageId;

    @NotNull
    @OneToOne
    @JoinColumn(name = "FROM_USER_ID")
    private User fromUser;

    @NotNull
    @OneToOne
    @JoinColumn(name = "TO_USER_ID")
    private User toUser;

    @NotNull
    @Column(name = "MESSAGE")
    private String message;

    @NotNull
    @Column(name = "DATE")
    private Timestamp date;
}
