package com.greethy.nutrition.core.port.in.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FindIngredientByIdQuery {

    private String ingredientId;
}