package com.ujazdowski.SocialPortal.model.tables;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "LANGUAGE_T")
public class Language {
    @Id
    @Column(name = "LANGUAGE_ID")
    private Long languageId;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Column(name="CODE")
    private String code;
}
