package com.greethy.nutritioncommon.dto.request.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greethy.nutritioncommon.dto.value.FoodIngredientDto;
import com.greethy.nutritioncommon.entity.enums.Group;
import com.greethy.nutritioncommon.entity.enums.Meal;
import lombok.Data;

import java.util.List;

@Data
public class UpdateFoodCommand {

    private String name;

    private Meal meal;

    private Group group;

    private String recipe;

    private String tips;

    @JsonProperty("more_info")
    private String moreInfo;

    @JsonProperty("food_ingredients")
    private List<FoodIngredientDto> foodIngredients;

}
