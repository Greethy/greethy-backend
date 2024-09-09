package com.greethy.nutritioncommon.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NutritionLabel {

    MAIN_COURSE("Main Course"),
    SIDE_DISH("Side Dish"),
    APPETIZER("Appetizer"),
    DESSERT("Dessert"),
    DRINK("Drink"),
    SOUP("Soup"),
    SALAD("Salad"),
    SANDWICH("Sandwich"),
    WRAP("Wrap"),
    PASTA("Pasta"),
    PIZZA("Pizza"),
    BURGER("Burger"),
    TACO("Taco"),
    SUSHI("Sushi");

    private final String name;

}
