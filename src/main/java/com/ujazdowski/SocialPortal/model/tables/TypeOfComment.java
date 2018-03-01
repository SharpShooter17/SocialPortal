package com.ujazdowski.SocialPortal.model.tables;

import com.ujazdowski.SocialPortal.model.enums.COMMENT;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "TYPE_OF_COMMENT_T")
public class TypeOfComment {

    @NotNull
    @Id
    @Column(name = "TYPE_OF_COMMENT_ID")
    private Long typeOfCommentId;

    @NotNull
    @Column(name = "TYPE")
    private COMMENT type;

}
