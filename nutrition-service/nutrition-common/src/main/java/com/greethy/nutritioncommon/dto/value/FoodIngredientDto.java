package com.greethy.nutritioncommon.dto.value;

import lombok.Data;

@Data
public class FoodIngredientDto {

    private String ingredientId;

    private Double value;

    private String unit;

    private String prepare;

}
