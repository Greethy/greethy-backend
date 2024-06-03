package com.greethy.nutritioncommon.entity.value;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Represents the Basal Metabolic Rate (BMR), which is used to calculate the minimum caloric intake necessary
 * for maintaining basic life functions at rest in a neutrally temperate environment.
 * <p>
 * The BMR is calculated by multiplying the caloric consumption per kilogram of body weight by the user's body weight.
 *
 *
 * @author KienThanh
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bmr {

    /**
     * Stores the BMR value per kilogram of body weight getting by retrieve BmrByAge collections.
     * This value indicates the number of calories required per kilogram of body weight
     * to maintain basic life functions of the body over a day. The unit is calories/kg.
     */
    @Field(name = "bmr_per_kg")
    private Double bmrPerKg;

    /**
     * Stores the total daily BMR value for the user based on their body weight.
     * This value is calculated by multiplying the {@link #bmrPerKg} value by the user's body weight.
     * It provides the total number of calories the body consumes in a day
     * to maintain life functions while at rest. The unit is calories/day.
     */
    @Field(name = "bmr_per_day")
    private Double bmrPerDay;
}
