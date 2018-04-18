package com.ujazdowski.SocialPortal.exceptions;

public class PostNotFoundException extends GenericError {
    public PostNotFoundException() {
        super("Such post not exists");
        super.setError("Not found");
        super.setStatus(404L);
        super.setException(PostNotFoundException.class);
    }
}
