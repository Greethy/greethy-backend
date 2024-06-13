package com.greethy.nutritioncommon.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FoodLabel {
    // Meal Types
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
    SUSHI("Sushi"),

    // Cuisines
    VIETNAMESE("Vietnamese"),
    ASIAN("Asian"),
    CHINESE("Chinese"),
    JAPANESE("Japanese"),
    KOREAN("Korean"),
    THAI("Thai"),
    INDIAN("Indian"),
    EUROPEAN("European"),
    FRENCH("French"),
    ITALIAN("Italian"),
    SPANISH("Spanish"),
    GREEK("Greek"),
    MIDDLE_EASTERN("Middle Eastern"),
    MEDITERRANEAN("Mediterranean"),
    LATIN_AMERICAN("Latin American"),
    MEXICAN("Mexican"),
    BRAZILIAN("Brazilian"),
    AFRICAN("African"),
    MOROCCAN("Moroccan"),
    ETHIOPIAN("Ethiopian"),
    NORTH_AMERICAN("North American"),
    AMERICAN("American"),
    CANADIAN("Canadian"),

    // Dietary Restrictions & Preferences
    VEGETARIAN("Vegetarian"),
    VEGAN("Vegan"),
    GLUTEN_FREE("Gluten-Free"),
    DAIRY_FREE("Dairy-Free"),
    LACTOSE_FREE("Lactose-Free"),
    PALEO("Paleo"),
    KETO("Keto"),
    LOW_SODIUM("Low Sodium"),
    LOW_FAT("Low Fat"),
    SUGAR_FREE("Sugar-Free"),
    HALAL("Halal"),
    KOSHER("Kosher"),

    // Cooking Methods
    BAKED("Baked"),
    GRILLED("Grilled"),
    FRIED("Fried"),
    STEAMED("Steamed"),
    SAUTEED("Sautéed"),
    ROASTED("Roasted"),
    BOILED("Boiled"),
    STIR_FRIED("Stir-Fried"),
    DEEP_FRIED("Deep-Fried"),

    // Main Ingredients
    CHICKEN("Chicken"),
    BEEF("Beef"),
    PORK("Pork"),
    LAMB("Lamb"),
    DUCK("Duck"),
    TURKEY("Turkey"),
    SEAFOOD("Seafood"),
    FISH("Fish"),
    SHRIMP("Shrimp"),
    CRAB("Crab"),
    LOBSTER("Lobster"),
    VEGETABLE("Vegetable"),
    FRUIT("Fruit"),
    GRAIN("Grain"),
    BEAN("Bean"),
    LENTIL("Lentil"),
    NUT("Nut"),
    EGG("Egg"),
    DAIRY("Dairy"),
    CHEESE("Cheese"),

    // Occasions
    BREAKFAST("Breakfast"),
    BRUNCH("Brunch"),
    LUNCH("Lunch"),
    DINNER("Dinner"),
    SNACK("Snack"),
    PARTY("Party"),
    POTLUCK("Potluck"),
    HOLIDAY("Holiday"),

    // Seasons
    SPRING("Spring"),
    SUMMER("Summer"),
    AUTUMN("Autumn"),
    WINTER("Winter"),

    // Difficulty Levels
    EASY("Easy"),
    MEDIUM("Medium"),
    HARD("Hard");

    private final String label;

}