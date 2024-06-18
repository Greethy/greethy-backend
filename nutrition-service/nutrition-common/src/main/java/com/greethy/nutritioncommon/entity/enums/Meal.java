package com.greethy.nutritioncommon.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Meal {

    BREAKFAST("Breakfast"),
    BRUNCH("Brunch"),
    LUNCH("Lunch"),
    DINNER("Dinner"),
    SNACK("Snack");

    private final String name;

}