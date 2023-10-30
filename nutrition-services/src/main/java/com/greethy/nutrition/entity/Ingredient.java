package com.greethy.nutrition.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "ingredients")
public class Ingredient extends BaseEntity {

    @Field(name = "name")
    private String name;

    @Field(name = "nutrients_per_100g")
    private List<Measure> nutrientsPer100g;

}
