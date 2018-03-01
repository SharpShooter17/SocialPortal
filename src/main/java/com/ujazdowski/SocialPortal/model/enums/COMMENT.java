package com.ujazdowski.SocialPortal.model.enums;

public enum COMMENT {
    POST("POST"),
    PICTRUE("PICTRUE");

    private final String comment;

    COMMENT(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString(){
        return this.comment;
    }
}
