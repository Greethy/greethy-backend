package com.greethy.nutrition.core.domain.entity;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FoodIngredient {

    @Field(name = "ingredient_id")
    private String ingredientId;

    private String name;

    private Double value;

    private String unit;

    private String prepare;

    private Double calories;
}
