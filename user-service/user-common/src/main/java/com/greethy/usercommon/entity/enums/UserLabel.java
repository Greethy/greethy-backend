package com.greethy.usercommon.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserLabel {

    VEGAN("Vegan"),
    VEGETARIAN("Vegetarian"),
    KETOGENIC("Ketogenic"),
    PALEO("Paleo"),
    LOW_CARB("Low-carb"),
    GLUTEN_FREE("Gluten-free"),
    NUT_ALLERGY("Nut-allergy"),
    DAIRY_FREE("Dairy-free"),
    WEIGHT_LOSS("Weight-loss"),
    MUSCLE_GAIN("Muscle-gain"),
    MAINTAIN_WEIGHT("Maintain-weight"),
    HIGH_PROTEIN("High-protein"),
    LOW_SODIUM("Low-sodium"),
    HIGH_FIBER("High-fiber"),
    DIABETES("Diabetes"),
    HYPERTENSION("Hypertension"),
    CHOLESTEROL("Cholesterol"),
    SEDENTARY("Sedentary"),
    MODERATELY_ACTIVE("Moderately"),
    HIGHLY_ACTIVE("Vigorous"),
    SPICY("Spicy"),
    SWEET("Sweet"),
    BITTER("Bitter"),
    SALTY("Salty"),
    SOUR("Sour"),
    HEALTHY("Healthy"),
    ORGANIC("Organic"),
    ETHNIC("Ethnic"),
    SEAFOOD("Seafood"),
    VEGGIES("Veggies"),
    MEAT("Meat"),
    DESSERTS("Desserts"),
    FAST_FOOD("Fast food"),
    RAW("Raw");

    private final String name;
}
