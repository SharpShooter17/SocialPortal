package com.ujazdowski.SocialPortal.model.forms;

import lombok.Data;

@Data
public class PostForm {
    private Long userId;
    private String text;
}
