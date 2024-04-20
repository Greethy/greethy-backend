package com.greethy.nutrition.core.domain.entity.menu;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "foods")
public class Food {

    @Id
    private String id;

    private String name;

    @Field(name = "food_type")
    private List<String> foodTypes;

    @Field(name = "image_url")
    private String imageUrl;

    @Field(name = "video_url")
    private String videoUrl;

    @Field(name = "total_calories")
    private Integer totalCalories;

    private String recipe;

    private String tips;

    private String locale;

    @Field(name = "created_at")
    private LocalDateTime createdAt;

    private List<Ingredient> ingredients;

    private List<Nutrient> nutrients;

}
