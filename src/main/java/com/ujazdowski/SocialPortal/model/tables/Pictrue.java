package com.ujazdowski.SocialPortal.model.tables;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Blob;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "PICTRUE_T")
public class Pictrue {
    @GeneratedValue
    @Id
    @Column(name = "PICTRUE_ID")
    private Long pictrueId;

    @NotNull
    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @NotNull
    @Column(name = "IMAGE")
    private Blob image;

    @NotNull
    @Column(name = "DATE")
    private Timestamp date;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "TYPE_ID")
    private ImgType imgType;
}
