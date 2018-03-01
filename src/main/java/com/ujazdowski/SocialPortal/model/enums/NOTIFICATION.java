package com.ujazdowski.SocialPortal.model.enums;

public enum NOTIFICATION {
    INVITATION("INVITATION"),
    NEW_COMMENT("NEW_COMMENT"),
    NEW_MESSAGE("NEW_MESSAGE");

    private final String notification;

    NOTIFICATION(String notification) {
        this.notification = notification;
    }

    @Override
    public String toString() {
        return this.notification;
    }
}
