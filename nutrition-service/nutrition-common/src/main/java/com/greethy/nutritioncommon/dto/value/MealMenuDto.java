package com.greethy.nutritioncommon.dto.value;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greethy.nutritioncommon.entity.value.FoodMenu;
import lombok.Data;

import java.util.List;

@Data
public class MealMenuDto {

    private String meal;

    private Double protein;

    private Double lipid;

    private Double carbohydrate;

    @JsonProperty("total_calories")
    private Integer totalCalories;

    private List<FoodMenu> foods;

    private Boolean status;

}
