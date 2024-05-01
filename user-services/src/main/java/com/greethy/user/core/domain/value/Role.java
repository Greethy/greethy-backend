package com.greethy.user.core.domain.value;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    ROLE_USER("USER"),
    ROLE_EXPERT("EXPERT"),
    ROLE_MERCHANT("MERCHANT"),
    ROLE_ADMIN("ADMIN");

    private final String type;
}