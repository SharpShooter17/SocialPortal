package com.ujazdowski.SocialPortal.exceptions;

public class NotValidUserAuthenticationException extends Exception {
    public NotValidUserAuthenticationException(){
        super("Password or email is not valid!");
    }
}
