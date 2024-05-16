package com.greethy.nutrition.core.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

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
