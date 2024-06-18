package com.greethy.nutritioncommon.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public enum NutritionType {

    REGULAR("Regular",
            List.of(Group.CEREAL, Group.DESERT),
            List.of(Group.CEREAL, Group.PROTEIN, Group.VEGETABLE, Group.DESERT),
            List.of(Group.CEREAL, Group.PROTEIN, Group.SOUP, Group.DESERT),
            List.of(Group.DESERT)
    ),
    LOW_CALORIE("Low Calorie",
            List.of(Group.FRUIT),
            List.of(Group.CEREAL, Group.PROTEIN, Group.VEGETABLE, Group.SOUP),
            List.of(Group.CEREAL, Group.VEGETABLE, Group.SOUP, Group.FRUIT),
            List.of(Group.FRUIT)
    ),
    HIGH_PROTEIN("High Protein",
            List.of(Group.CEREAL, Group.PROTEIN),
            List.of(Group.CEREAL, Group.PROTEIN, Group.PROTEIN, Group.SOUP, Group.VEGETABLE),
            List.of(Group.CEREAL, Group.PROTEIN, Group.PROTEIN, Group.DESERT),
            List.of(Group.DESERT)
    ),
    VEGETARIAN("Vegetarian",
            List.of(Group.CEREAL, Group.FRUIT),
            List.of(Group.VEGETABLE, Group.SOUP, Group.CEREAL),
            List.of(Group.VEGETABLE, Group.CEREAL),
            List.of(Group.FRUIT)
    ),
    KETO("Keto",
            List.of(Group.PROTEIN),
            List.of(Group.PROTEIN, Group.VEGETABLE),
            List.of(Group.PROTEIN, Group.VEGETABLE),
            List.of(Group.PROTEIN)
    ),
    DIABETIC("Diabetic",
            List.of(Group.CEREAL),
            List.of(Group.CEREAL, Group.PROTEIN, Group.VEGETABLE),
            List.of(Group.CEREAL, Group.PROTEIN),
            List.of(Group.FRUIT)
    ),
    HIGH_FIBER("High Fiber",
            List.of(Group.CEREAL, Group.FRUIT),
            List.of(Group.VEGETABLE, Group.CEREAL),
            List.of(Group.VEGETABLE, Group.CEREAL),
            List.of(Group.FRUIT)
    ),
    LOW_CARB("Low Carb",
            List.of(Group.PROTEIN, Group.FRUIT),
            List.of(Group.PROTEIN, Group.VEGETABLE),
            List.of(Group.PROTEIN, Group.VEGETABLE),
            List.of(Group.FRUIT)
    ),
    HEART_HEALTHY("Heart Healthy",
            List.of(Group.CEREAL, Group.FRUIT),
            List.of(Group.CEREAL, Group.VEGETABLE, Group.PROTEIN),
            List.of(Group.CEREAL, Group.VEGETABLE),
            List.of(Group.FRUIT)
    ),
    PREGNANCY("Pregnancy",
            List.of(Group.CEREAL, Group.FRUIT),
            List.of(Group.CEREAL, Group.PROTEIN, Group.VEGETABLE),
            List.of(Group.CEREAL, Group.PROTEIN, Group.VEGETABLE),
            List.of(Group.FRUIT, Group.DESERT)
    ),
    SENIOR("Senior",
            List.of(Group.CEREAL, Group.FRUIT),
            List.of(Group.CEREAL, Group.PROTEIN, Group.VEGETABLE),
            List.of(Group.CEREAL, Group.PROTEIN),
            List.of(Group.FRUIT)
    ),
    ATHLETE("Athlete",
            List.of(Group.CEREAL, Group.PROTEIN),
            List.of(Group.CEREAL, Group.PROTEIN, Group.PROTEIN, Group.VEGETABLE),
            List.of(Group.CEREAL, Group.PROTEIN, Group.PROTEIN),
            List.of(Group.PROTEIN)
    ),
    MENTAL_HEALTH("Mental Health",
            List.of(Group.CEREAL, Group.FRUIT),
            List.of(Group.VEGETABLE, Group.CEREAL, Group.PROTEIN),
            List.of(Group.VEGETABLE, Group.CEREAL),
            List.of(Group.FRUIT)
    ),
    DIGESTIVE_HEALTH("Digestive Health",
            List.of(Group.CEREAL, Group.FRUIT),
            List.of(Group.VEGETABLE, Group.CEREAL),
            List.of(Group.VEGETABLE, Group.CEREAL),
            List.of(Group.FRUIT)
    ),
    HYPOALLERGENIC("Hypoallergenic",
            List.of(Group.CEREAL),
            List.of(Group.VEGETABLE, Group.PROTEIN),
            List.of(Group.VEGETABLE),
            List.of(Group.FRUIT)
    ),
    GLUTEN_FREE("Gluten Free",
            List.of(Group.PROTEIN, Group.FRUIT),
            List.of(Group.PROTEIN, Group.VEGETABLE),
            List.of(Group.PROTEIN, Group.VEGETABLE),
            List.of(Group.FRUIT)
    );

    private final String name;

    private final List<Group> breakfastGroup;

    private final List<Group> lunchGroup;

    private final List<Group> dinnerGroup;

    private final List<Group> snackGroup;

}
