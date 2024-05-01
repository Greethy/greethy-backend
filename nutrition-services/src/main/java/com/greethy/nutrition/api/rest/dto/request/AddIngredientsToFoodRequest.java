package com.greethy.nutrition.api.rest.dto.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
public class AddIngredientsToFoodRequest {

    private List<FoodIngredient> ingredients;

    @Data
    @ToString
    public static class FoodIngredient {

        @JsonProperty("ingredient_id")
        private String ingredientId;

        private Double value;

        private String unit;

        private String prepare;
    }
}
