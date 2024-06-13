package com.greethy.nutritioncommon.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MenuLabel {

    // Calories
    LOW_CALORIE("Low Calorie"),
    MEDIUM_CALORIE("Medium Calorie"),
    HIGH_CALORIE("High Calorie"),

    // Macronutrients
    HIGH_PROTEIN("High Protein"),
    MODERATE_PROTEIN("Moderate Protein"),
    LOW_PROTEIN("Low Protein"),
    HIGH_CARB("High Carb"),
    MODERATE_CARB("Moderate Carb"),
    LOW_CARB("Low Carb"),
    HIGH_FAT("High Fat"),
    MODERATE_FAT("Moderate Fat"),
    LOW_FAT("Low Fat"),

    // Fiber
    HIGH_FIBER("High Fiber"),
    GOOD_SOURCE_OF_FIBER("Good Source of Fiber"),
    LOW_FIBER("Low Fiber"),

    // Sugar (Đường)
    LOW_SUGAR("Low Sugar"),
    MODERATE_SUGAR("Moderate Sugar"),
    HIGH_SUGAR("High Sugar"),

    // Sodium (Natri)
    LOW_SODIUM("Low Sodium"),
    MODERATE_SODIUM("Moderate Sodium"),
    HIGH_SODIUM("High Sodium"),

    // Cholesterol (Cholesterol)
    LOW_CHOLESTEROL("Low Cholesterol"),
    MODERATE_CHOLESTEROL("Moderate Cholesterol"),
    HIGH_CHOLESTEROL("High Cholesterol"),

    // Dietary Restrictions & Preferences
    VEGETARIAN("Vegetarian"),
    VEGAN("Vegan"),
    GLUTEN_FREE("Gluten-Free"),
    DAIRY_FREE("Dairy-Free"),
    LACTOSE_FREE("Lactose-Free"),
    PALEO("Paleo"),
    KETO("Keto"),
    HALAL("Halal"),
    KOSHER("Kosher"),
    NUT_FREE("Nut-Free"),
    SOY_FREE("Soy-Free"),
    EGG_FREE("Egg-Free"),

    // Health & Wellness
    HEART_HEALTHY("Heart Healthy"),
    DIABETIC_FRIENDLY("Diabetic Friendly"),
    WEIGHT_LOSS("Weight Loss"),
    MUSCLE_BUILDING("Muscle Building"),
    GUT_HEALTH("Gut Health"),
    ANTI_INFLAMMATORY("Anti-Inflammatory"),
    IMMUNE_BOOSTING("Immune Boosting"),

    // Meal Types
    BREAKFAST("Breakfast"),
    BRUNCH("Brunch"),
    LUNCH("Lunch"),
    DINNER("Dinner"),
    SNACK("Snack"),
    DESSERT("Dessert"),

    // Cuisines
    VIETNAMESE("Vietnamese"),
    ASIAN("Asian"),
    EUROPEAN("European"),
    MIDDLE_EASTERN("Middle Eastern"),
    MEDITERRANEAN("Mediterranean"),
    LATIN_AMERICAN("Latin American"),
    AFRICAN("African"),
    NORTH_AMERICAN("North American"),

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
    SEAFOOD("Seafood"),
    SHRIMP("Shrimp"),
    VEGETABLE("Vegetable"),
    FRUIT("Fruit"),
    GRAIN("Grain"),
    BEAN("Bean"),
    LENTIL("Lentil"),
    NUT("Nut"),
    EGG("Egg"),
    DAIRY("Dairy"),
    CHEESE("Cheese"),

    // Allergies
    PEANUTS("Peanuts"),
    TREE_NUTS("Tree Nuts"),
    SOY("Soy"),
    WHEAT("Wheat"),
    MILK("Milk"),
    EGGS("Eggs"),
    FISH("Fish"),
    SHELLFISH("Shellfish");

    private final String label;

}

