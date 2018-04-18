package com.ujazdowski.SocialPortal.exceptions;

public class FileNotValidException extends GenericError {
    public FileNotValidException() {
        super("File is not valid exception");
        super.setException(FileNotValidException.class);
        super.setStatus(400L);
        super.setError("Bad Request");
    }
}
