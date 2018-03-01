package com.ujazdowski.SocialPortal.model.tables;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "USER_ROLE_T")
public class UserRole {
    @Id
    @NotNull
    @Column(name = "USER_ROLE_ID")
    private Long userRoleId;

    @NotNull
    @Column(name = "ROLE")
    private String role;
}
