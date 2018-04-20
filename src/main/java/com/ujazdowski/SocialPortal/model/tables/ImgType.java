package com.ujazdowski.SocialPortal.model.tables;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "TYPES_OF_PICTRUE_T")
public class ImgType {
    @Id
    @GeneratedValue
    @Column(name = "TYPES_OF_PICTRUE_ID")
    private Long imgTypeId;

    @Column(name = "TYPE")
    private String type;
}
