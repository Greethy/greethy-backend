package com.greethy.nutrition.core.event;

import com.greethy.nutrition.core.domain.entity.FoodIngredient;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class IngredientsAddedToFoodEvent {

    private String foodId;

    private Double totalCalories;

    private List<FoodIngredient> foodIngredients;

    private LocalDateTime updatedAt;

}
