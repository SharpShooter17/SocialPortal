package com.ujazdowski.SocialPortal.model.tables;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "ROLE_T")
public class Role {

    @Id
    @NotNull
    @Column(name = "ROLE_ID")
    private Long userRoleId;

    @NotNull
    @Column(name = "ROLE")
    private String role;
}
