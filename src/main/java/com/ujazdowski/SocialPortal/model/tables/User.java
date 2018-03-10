package com.ujazdowski.SocialPortal.model.tables;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLES_T",
                joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")},
                inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID", unique = true)})
    private Set<Role> roles = new HashSet<>();

    @NotNull
    @Size(min = 2, max = 64, message = "{valid.register.firstName.size}")
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotNull
    @Size(min = 2, max = 64, message ="{valid.register.secondName.size}")
    @Column(name = "SECOND_NAME")
    private String secondName;

    @Email(message = "{valid.register.email}")
    @NotNull
    @Column(name = "EMAIL")
    private String email;

    @NotNull
    //@Size(min = 8, max = 32, message = "valid.register.password")
    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "LAST_TIME_ONLINE")
    private Timestamp lastTimeOnline;

    @Column(name = "PROFILE_PHOTO_ID")
    private Long profilePhotoId;

    @NotNull
    @Column(name = "MALE")
    private Boolean male;
}
