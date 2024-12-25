package com.greethy.account.auth.domain.entity.valueobject;

public record Credential (String type, String value, boolean temporary) {

    private static final String DEFAULT_TYPE = "password";
    private static final boolean DEFAULT_TEMPORARY = false;

    public Credential(String value) {
        this(DEFAULT_TYPE, value, DEFAULT_TEMPORARY);
    }

}
