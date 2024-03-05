package com.greethy.nutrition.core.domain.entity.food_menu;

import lombok.Data;

import java.util.List;

@Data
public class Meal {

    private String name;

    private List<String> foodIds;

    private Integer calories;

}
