package com.greethy.nutrition.core.domain.entity.specs;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {

    FEMALE("female", 0),
    MALE("male", 1);

    private final String name;

    private final Integer value;

}
