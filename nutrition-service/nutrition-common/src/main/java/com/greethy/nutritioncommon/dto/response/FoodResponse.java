package com.greethy.nutritioncommon.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greethy.nutritioncommon.dto.value.FoodIngredientDto;
import com.greethy.nutritioncommon.dto.value.NutrientDto;
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

    private String group;

    private String meal;

    private String recipe;

    private String tips;

    private String description;

    @JsonProperty("image_urls")
    private List<String> imageUrls;

    @JsonProperty("video")
    private String video;

    private List<String> labels;

    @JsonProperty("total_calories")
    private Integer totalCalories;

    @JsonProperty("food_ingredients")
    private List<FoodIngredientDto> foodIngredients;

    private List<NutrientDto> nutrients;

}
