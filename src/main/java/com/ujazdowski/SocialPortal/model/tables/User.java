package com.ujazdowski.SocialPortal.model.tables;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.xml.bind.annotation.XmlRootElement;

import java.sql.Timestamp;
import java.util.Date;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

@Data
@Entity
@Table(name = "USER_T")
@XmlRootElement
public class User {
    @Id
    @GeneratedValue
    @Column(name = "USER_ID", unique=true)
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

    @Email
    @NotNull
    @Column(name = "EMAIL")
    private String email;

    @NotNull
    @Column(name = "PASSWORD")
    private String password;
/*
    @NotNull
    @Column(name = "BIRTHDAY")
    private Date birthday;*/

    @Column(name = "LAST_TIME_ONLINE")
    private Timestamp lastTimeOnline;
/*
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "POSTAL_CODE")
    private String postalCode;

    @Column(name = "STREET")
    private String street;

    @Column(name = "CITY")
    private String city;
*/
    @Column(name = "PROFILE_PHOTO_ID")
    private Long profilePhotoId;

    @NotNull
    @Column(name = "MALE")
    private Boolean male;
}
