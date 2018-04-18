package com.ujazdowski.SocialPortal.exceptions;

import java.util.Date;

public class UserNotExistsException extends GenericError {
    public UserNotExistsException(){
        super("User not exists!");
        super.setStatus(404L);
        super.setError("Not found");
        super.setException(UserExistsException.class);
    }
}
