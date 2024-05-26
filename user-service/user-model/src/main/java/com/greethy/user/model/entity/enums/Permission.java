package com.greethy.user.model.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Permission {

    READ("read"),
    WRITE("write"),
    DELETE("delete");

    private final String name;
}
