package com.ujazdowski.SocialPortal.exceptions;

public class UserExistsException extends Exception {
    public UserExistsException(){
        super("This email is existing in database!");
    }
}
