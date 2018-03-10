package com.ujazdowski.SocialPortal.exceptions;

import com.ujazdowski.SocialPortal.model.tables.User;

public class UserExistsException extends Exception {
    public UserExistsException(){
        super("This email is existing in database!");
    }
}
