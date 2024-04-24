package com.greethy.nutrition.core.port.in.command;

import com.greethy.nutrition.core.domain.entity.FoodIngredient;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

@Data
public class AddIngredientsToFoodCommand {

    @TargetAggregateIdentifier
    private String foodId;

    private List<FoodIngredient> foodIngredients;

}
