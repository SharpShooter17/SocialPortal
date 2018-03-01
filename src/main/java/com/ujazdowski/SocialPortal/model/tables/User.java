package com.ujazdowski.SocialPortal.model.tables;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import java.sql.Timestamp;
import java.util.Date;
import lombok.Data;

@Data
@Entity
@Table(name = "USER_T")
public class User {
    @Id
    @NotNull
    @Column(name = "USER_ID")
    private Long userId;

    @OneToOne
    @JoinColumn(name = "ROLE_ID")
    private UserRole role;

    @NotNull
    @OneToOne
    @JoinColumn(name = "PREFERRED_LANGUAGE_ID")
    private Language preferredLanguage;

    @NotNull
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotNull
    @Column(name = "SECOND_NAME")
    private String secondName;

    @NotNull
    @Column(name = "EMAIL")
    private String email;

    @NotNull
    @Column(name = "PASSWORD")
    private String password;

    @NotNull
    @Column(name = "BIRTHDAY")
    private Date birthday;

    @Column(name = "LAST_TIME_ONLINE")
    private Timestamp lastTimeOnline;

    @Null
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Null
    @Column(name = "POSTAL_CODE")
    private String postalCode;

    @Null
    @Column(name = "STREET")
    private String street;

    @Null
    @Column(name = "CITY")
    private String city;

    @Null
    @Column(name = "PROFILE_PHOTO_ID")
    private Long profilePhotoId;
}
