package com.greethy.nutrition.core.domain.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.greethy.nutrition.core.domain.value.Nutrient;

import lombok.Data;

@Data
@Document(collection = "ingredients")
public class Ingredient {

    @Id
    private String id;

    private String name;

    private String code;

    private String locale;

    private String category;

    private Integer edible;

    private Integer calories;

    @Field(name = "nutrition_per_100g")
    private List<Nutrient> nutrients;
}
