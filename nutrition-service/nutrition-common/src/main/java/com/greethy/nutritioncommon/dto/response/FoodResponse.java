package com.greethy.nutritioncommon.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greethy.nutritioncommon.dto.value.FoodIngredientDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class FoodResponse {

    private String id;

    private String name;

    private String meal;

    private String group;

    private String recipe;

    private String tips;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("instruction_url")
    private String instructionUrl;

    private List<String> labels;

    private List<FoodIngredientDto> ingredients;

    @JsonProperty("total_calories")
    private Integer totalCalories;

}
