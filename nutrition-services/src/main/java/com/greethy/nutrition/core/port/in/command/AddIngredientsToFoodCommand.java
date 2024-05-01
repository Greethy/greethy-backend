package com.greethy.nutrition.core.port.in.command;

import java.util.List;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.greethy.nutrition.core.domain.entity.FoodIngredient;

import lombok.Data;

@Data
public class AddIngredientsToFoodCommand {

    @TargetAggregateIdentifier
    private String foodId;

    private List<FoodIngredient> foodIngredients;
}
