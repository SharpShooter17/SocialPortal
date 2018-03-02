package com.ujazdowski.SocialPortal.exceptions;

import lombok.Data;

@Data
public class ExceptionMessage {
    private String error;
    public ExceptionMessage(String error){
        this.error = error;
    }
}
