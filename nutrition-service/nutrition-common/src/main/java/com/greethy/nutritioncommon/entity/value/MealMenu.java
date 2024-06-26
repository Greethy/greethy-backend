package com.greethy.nutritioncommon.entity.value;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MealMenu {

    private String meal;

    private Double protein;

    private Double lipid;

    private Double carbohydrate;

    private Integer totalCalories;

    private List<FoodMenu> foods;

    private Boolean status;
}
