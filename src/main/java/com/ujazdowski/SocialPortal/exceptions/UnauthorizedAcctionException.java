package com.ujazdowski.SocialPortal.exceptions;

public class UnauthorizedAcctionException extends GenericError {
    public UnauthorizedAcctionException(){
        super("Unauthorized acction!");
        super.setException(UnauthorizedAcctionException.class);
        super.setStatus(401L);
        super.setError("Unauthorized");
    }
}
