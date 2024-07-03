package com.greethy.nutritioncommon.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public enum NutritionType {

    REGULAR("Regular",
            List.of(FoodGroup.CEREAL, FoodGroup.DESERT),
            List.of(FoodGroup.CEREAL, FoodGroup.PROTEIN, FoodGroup.VEGETABLE, FoodGroup.DESERT),
            List.of(FoodGroup.CEREAL, FoodGroup.PROTEIN, FoodGroup.SOUP, FoodGroup.DESERT),
            List.of(FoodGroup.DESERT)
    ),
    LOW_CALORIE("Low Calorie",
            List.of(FoodGroup.FRUIT),
            List.of(FoodGroup.CEREAL, FoodGroup.PROTEIN, FoodGroup.VEGETABLE, FoodGroup.SOUP),
            List.of(FoodGroup.CEREAL, FoodGroup.VEGETABLE, FoodGroup.SOUP, FoodGroup.FRUIT),
            List.of(FoodGroup.FRUIT)
    ),
    HIGH_PROTEIN("High Protein",
            List.of(FoodGroup.CEREAL, FoodGroup.PROTEIN),
            List.of(FoodGroup.CEREAL, FoodGroup.PROTEIN, FoodGroup.PROTEIN, FoodGroup.SOUP, FoodGroup.VEGETABLE),
            List.of(FoodGroup.CEREAL, FoodGroup.PROTEIN, FoodGroup.PROTEIN, FoodGroup.DESERT),
            List.of(FoodGroup.DESERT)
    ),
    VEGETARIAN("Vegetarian",
            List.of(FoodGroup.CEREAL, FoodGroup.FRUIT),
            List.of(FoodGroup.VEGETABLE, FoodGroup.SOUP, FoodGroup.CEREAL),
            List.of(FoodGroup.VEGETABLE, FoodGroup.CEREAL),
            List.of(FoodGroup.FRUIT)
    ),
    KETO("Keto",
            List.of(FoodGroup.PROTEIN),
            List.of(FoodGroup.PROTEIN, FoodGroup.VEGETABLE),
            List.of(FoodGroup.PROTEIN, FoodGroup.VEGETABLE),
            List.of(FoodGroup.PROTEIN)
    ),
    DIABETIC("Diabetic",
            List.of(FoodGroup.CEREAL),
            List.of(FoodGroup.CEREAL, FoodGroup.PROTEIN, FoodGroup.VEGETABLE),
            List.of(FoodGroup.CEREAL, FoodGroup.PROTEIN),
            List.of(FoodGroup.FRUIT)
    ),
    HIGH_FIBER("High Fiber",
            List.of(FoodGroup.CEREAL, FoodGroup.FRUIT),
            List.of(FoodGroup.VEGETABLE, FoodGroup.CEREAL),
            List.of(FoodGroup.VEGETABLE, FoodGroup.CEREAL),
            List.of(FoodGroup.FRUIT)
    ),
    LOW_CARB("Low Carb",
            List.of(FoodGroup.PROTEIN, FoodGroup.FRUIT),
            List.of(FoodGroup.PROTEIN, FoodGroup.VEGETABLE),
            List.of(FoodGroup.PROTEIN, FoodGroup.VEGETABLE),
            List.of(FoodGroup.FRUIT)
    ),
    HEART_HEALTHY("Heart Healthy",
            List.of(FoodGroup.CEREAL, FoodGroup.FRUIT),
            List.of(FoodGroup.CEREAL, FoodGroup.VEGETABLE, FoodGroup.PROTEIN),
            List.of(FoodGroup.CEREAL, FoodGroup.VEGETABLE),
            List.of(FoodGroup.FRUIT)
    ),
    PREGNANCY("Pregnancy",
            List.of(FoodGroup.CEREAL, FoodGroup.FRUIT),
            List.of(FoodGroup.CEREAL, FoodGroup.PROTEIN, FoodGroup.VEGETABLE),
            List.of(FoodGroup.CEREAL, FoodGroup.PROTEIN, FoodGroup.VEGETABLE),
            List.of(FoodGroup.FRUIT, FoodGroup.DESERT)
    ),
    SENIOR("Senior",
            List.of(FoodGroup.CEREAL, FoodGroup.FRUIT),
            List.of(FoodGroup.CEREAL, FoodGroup.PROTEIN, FoodGroup.VEGETABLE),
            List.of(FoodGroup.CEREAL, FoodGroup.PROTEIN),
            List.of(FoodGroup.FRUIT)
    ),
    ATHLETE("Athlete",
            List.of(FoodGroup.CEREAL, FoodGroup.PROTEIN),
            List.of(FoodGroup.CEREAL, FoodGroup.PROTEIN, FoodGroup.PROTEIN, FoodGroup.VEGETABLE),
            List.of(FoodGroup.CEREAL, FoodGroup.PROTEIN, FoodGroup.PROTEIN),
            List.of(FoodGroup.PROTEIN)
    ),
    MENTAL_HEALTH("Mental Health",
            List.of(FoodGroup.CEREAL, FoodGroup.FRUIT),
            List.of(FoodGroup.VEGETABLE, FoodGroup.CEREAL, FoodGroup.PROTEIN),
            List.of(FoodGroup.VEGETABLE, FoodGroup.CEREAL),
            List.of(FoodGroup.FRUIT)
    ),
    DIGESTIVE_HEALTH("Digestive Health",
            List.of(FoodGroup.CEREAL, FoodGroup.FRUIT),
            List.of(FoodGroup.VEGETABLE, FoodGroup.CEREAL),
            List.of(FoodGroup.VEGETABLE, FoodGroup.CEREAL),
            List.of(FoodGroup.FRUIT)
    ),
    HYPOALLERGENIC("Hypoallergenic",
            List.of(FoodGroup.CEREAL),
            List.of(FoodGroup.VEGETABLE, FoodGroup.PROTEIN),
            List.of(FoodGroup.VEGETABLE),
            List.of(FoodGroup.FRUIT)
    ),
    GLUTEN_FREE("Gluten Free",
            List.of(FoodGroup.PROTEIN, FoodGroup.FRUIT),
            List.of(FoodGroup.PROTEIN, FoodGroup.VEGETABLE),
            List.of(FoodGroup.PROTEIN, FoodGroup.VEGETABLE),
            List.of(FoodGroup.FRUIT)
    );

    private final String name;

    private final List<FoodGroup> breakfastGroup;

    private final List<FoodGroup> lunchGroup;

    private final List<FoodGroup> dinnerGroup;

    private final List<FoodGroup> snackGroup;

}
