package com.greethy.nutritioncommon.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodIngredient {

    @Field(name = "ingredient_id")
    private String ingredientId;

    private String name;

    private Double value;

    private String unit;

    private String prepare;

    private Double calories;

}
