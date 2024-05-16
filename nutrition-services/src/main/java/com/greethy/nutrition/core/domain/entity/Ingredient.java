package com.greethy.nutrition.core.domain.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.greethy.nutrition.core.domain.value.Nutrient;

import lombok.Data;

@Data
@Document(collection = "ingredients")
public class Ingredient {

    @MongoId(FieldType.STRING)
    private String id;

    private String name;

    private String locale;

    private String category;

    private Integer edible;

    private Integer calories;

    @Field(name = "nutrition_per_100g")
    private Nutrient nutrient;
}
