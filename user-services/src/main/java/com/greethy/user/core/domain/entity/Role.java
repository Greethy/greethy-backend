package com.greethy.user.core.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

    ROLE_USER("ROLE_USER"),
    ROLE_EXPERT("ROLE_EXPERT"),
    ROLE_MERCHANT("ROLE_MERCHANT"),
    ROLE_ADMIN("ROLE_ADMIN");

    private final String type;
}
