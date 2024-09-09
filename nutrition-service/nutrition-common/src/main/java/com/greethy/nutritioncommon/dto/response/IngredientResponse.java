package com.greethy.nutritioncommon.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greethy.nutritioncommon.entity.value.Nutrient;
import lombok.Data;

import java.util.List;

@Data
public class IngredientResponse {

    private String id;

    private String name;

    private String code;

    private Integer edible;

    private String group;

    private String description;

    @JsonProperty("calories_per_100g")
    private Integer caloriesPer100g;

    @JsonProperty("nutrition_per_100g")
    private List<Nutrient> nutritionPer100g;

}
