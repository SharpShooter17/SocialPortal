package com.ujazdowski.SocialPortal.model.tables;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "COMMENT_T")
public class Comment {
    @GeneratedValue
    @Id
    @Column(name = "COMMENT_ID")
    private Long commentId;

    @NotNull
    @Column(name = "COMMENT")
    private String comment;

    @NotNull
    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @NotNull
    @Column(name = "REFERENCE_ID")
    private Long refId;

    @NotNull
    @OneToOne
    @JoinColumn(name = "TYPE_OF_COMMENT_ID")
    private TypeOfComment type;
}
