package com.greethy.nutritioncommon.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActivityLevel {

    SEDENTARY("sedentary"),
    MODERATELY("moderately"),
    VIGOROUS("vigorous");

    private final String name;
}
