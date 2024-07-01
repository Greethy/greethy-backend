package com.greethy.nutritioncommon.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FoodGroup {

    CEREAL("cereal"),
    SOUP("soup"),
    PROTEIN("protein"),
    VEGETABLE("vegetable"),
    DESERT("desert"),
    FRUIT("fruit"),
    DRINK("drink");

    private final String name;
}
