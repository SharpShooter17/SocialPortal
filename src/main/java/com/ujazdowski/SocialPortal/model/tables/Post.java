package com.ujazdowski.SocialPortal.model.tables;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "POST_T")
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "POST_ID")
    private Long postId;

    @NotNull
    @Column(name = "TEXT")
    private String text;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @NotNull
    @Column(name = "DATE")
    private Date date;
}
