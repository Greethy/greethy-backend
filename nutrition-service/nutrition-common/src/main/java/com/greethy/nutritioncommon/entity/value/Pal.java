package com.greethy.nutritioncommon.entity.value;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * The {@code Pal} class represents the Physical Activity Level (PAL), which is a numerical scale that quantifies physical activity.
 * It is used to adjust energy expenditure based on activity levels and can vary depending on an individual's daily activities.
 * This class stores the descriptive name of the activity level and its corresponding numerical value.
 *
 * @author KienThanh
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pal {

    /**
     * The descriptive name of the activity level
     * (e.g., "Sedentary", "Moderately active", "Vigorously active").
     */
    @Field(name = "activity_level")
    private String activityLevel;

    /**
     * The numerical value associated with the activity level,
     * used to calculate or adjust caloric needs based on activity.
     */
    private Double value;
}
