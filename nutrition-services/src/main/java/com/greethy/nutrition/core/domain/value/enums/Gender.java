package com.greethy.nutrition.core.domain.value.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {
    FEMALE("female"),
    MALE("male");

    private final String name;

}
