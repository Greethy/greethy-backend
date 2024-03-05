package com.greethy.nutrition.core.domain.entity.food_menu;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "ingredients")
public class Ingredient {

    @Id
    private String id;

    private String name;

    @Field(name = "nutrition_per_100g")
    private List<Nutrient> nutrients;

}
