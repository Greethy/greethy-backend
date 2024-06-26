package com.greethy.nutritioncommon.dto.value;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FoodIngredientDto {

    @JsonProperty("ingredient_id")
    private String ingredientId;

    private Double value;

    private String unit;

    private String prepare;

}
