package com.greethy.nutrition.core.domain.entity.menu;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "foods")
public class Food {

    @Id
    private String id;

    private String name;

    @Field(name = "food_type")
    private String foodType;

    private String imageUrl;

    @Field(name = "cooking_video")
    private String cookingVideo;

    @Field(name = "total_calories")
    private Integer totalCalories;

    private String recipe;

    private String tips;

    private List<Ingredient> ingredients;

    private List<Nutrient> nutrients;

    private String locale;

}
