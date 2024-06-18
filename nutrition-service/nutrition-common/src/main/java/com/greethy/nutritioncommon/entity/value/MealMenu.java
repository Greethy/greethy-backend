package com.greethy.nutritioncommon.entity.value;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MealMenu {

    private String meal;

    private Double protein;

    private Double lipid;

    private Double carbohydrate;

    private Integer calories;

    private List<FoodMenu> foods;

    private Boolean status;
}
