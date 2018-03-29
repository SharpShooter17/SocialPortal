package com.ujazdowski.SocialPortal.model.tables;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

@Getter
@Setter
@Entity
@Table(name = "USER_T")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_EMPTY)
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

    @Column(name = "PROFILE_PHOTO_ID")
    private Long profile;

    @NotNull
    @Column(name = "MALE")
    private Boolean male;

    @NotNull
    @OneToOne
    @JoinColumn(name = "PREFERRED_LANGUAGE_ID", referencedColumnName = "LANGUAGE_ID")
    private Language language;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "FOR_USER_ID", referencedColumnName = "USER_ID")
    private Set<InvitationNotification> invitationsNotifications = new HashSet<>();
}
