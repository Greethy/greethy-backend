package com.greethy.nutrition.core.domain.value.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Group {

    CEREAL("cereal"),
    SOUP("soup"),
    PROTEIN("protein"),
    VEGETABLE("vegetable"),
    DESERT("desert"),
    FRUIT("fruit");

    private final String name;
}
