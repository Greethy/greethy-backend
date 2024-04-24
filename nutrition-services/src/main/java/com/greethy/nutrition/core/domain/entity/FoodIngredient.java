package com.greethy.nutrition.core.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FoodIngredient {

    private String ingredientId;

    private String name;

    private Double value;

    private String unit;

    private String prepare;

    private Double calories;

}
