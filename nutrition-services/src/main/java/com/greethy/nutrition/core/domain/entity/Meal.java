package com.greethy.nutrition.core.domain.entity;

import java.util.List;

import lombok.Data;

@Data
public class Meal {

    private String name;

    private List<String> foodIds;

    private Integer calories;
}
