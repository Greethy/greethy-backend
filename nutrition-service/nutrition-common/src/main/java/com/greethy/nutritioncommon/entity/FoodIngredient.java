package com.greethy.nutritioncommon.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
public class FoodIngredient {

    @Field(name = "ingredient_id")
    private String ingredientId;

    private String name;

    private Integer value;

    private String unit;

    private String prepare;

    private Double calories;

}
