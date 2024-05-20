package com.greethy.nutrition.core.event;

import java.util.List;

import com.greethy.nutrition.core.domain.entity.FoodIngredient;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IngredientsAddedToFoodEvent {

    private String foodId;

    private Double totalCalories;

    private List<FoodIngredient> foodIngredients;

}
