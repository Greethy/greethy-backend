package com.greethy.nutritioncommon.entity;

import com.greethy.common.domain.entity.BaseEntity;
import com.greethy.nutritioncommon.entity.value.Nutrient;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "foods")
@EqualsAndHashCode(callSuper = true)
public class Food extends BaseEntity {

    @Id
    private String id;

    private String name;

    private String group;

    private String meal;

    private String recipe;

    private String tips;

    private String description;

    @Field(name = "image_urls")
    private List<String> imageUrls;

    @Field(name = "video")
    private String video;

    @Field(name = "more_info")
    private String moreInfo;

    private List<String> labels;

    @Field(name = "total_calories")
    private Double totalCalories;

    @Field(name = "food_ingredients")
    private List<FoodIngredient> foodIngredients;

    private List<Nutrient> nutrients;

}
