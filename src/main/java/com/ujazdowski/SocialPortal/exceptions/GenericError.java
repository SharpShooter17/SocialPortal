package com.ujazdowski.SocialPortal.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class GenericError extends RuntimeException {
    protected Date date;
    protected String error;
    protected Long status;
    protected Class exception;

    GenericError(String message){
        super(message);
        date = new Date();
    }
}
