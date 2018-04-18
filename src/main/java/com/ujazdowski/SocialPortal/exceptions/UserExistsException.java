package com.ujazdowski.SocialPortal.exceptions;

import com.ujazdowski.SocialPortal.model.tables.User;

public class UserExistsException extends GenericError {
    public UserExistsException(){
        super("This email is existing in database!");
        super.setException(UserExistsException.class);
        super.setStatus(409L);
        super.setError("Conflict");
    }
}
