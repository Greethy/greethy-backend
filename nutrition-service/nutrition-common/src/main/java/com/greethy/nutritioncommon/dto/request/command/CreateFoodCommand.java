package com.greethy.nutritioncommon.dto.request.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greethy.nutritioncommon.dto.value.FoodIngredientDto;
import com.greethy.nutritioncommon.entity.enums.FoodGroup;
import com.greethy.nutritioncommon.entity.enums.Meal;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodCommand {

    @NotBlank(message = "command.create.food.name.not-blank")
    private String name;

    private Meal meal;

    private FoodGroup group;

    @NotBlank(message = "command.create.food.recipe.not-blank")
    private String recipe;

    @NotBlank(message = "command.create.food.tips.not-blank")
    private String tips;

    @JsonProperty("more_info")
    private String moreInfo;

    @JsonProperty("food_ingredients")
    private List<FoodIngredientDto> foodIngredients;

}
