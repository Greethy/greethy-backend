package com.greethy.nutritioncommon.entity;

import com.greethy.common.domain.entity.BaseEntity;
import com.greethy.nutritioncommon.entity.value.Nutrient;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;


@Data
@Builder
@Document(collection = "foods")
@EqualsAndHashCode(callSuper = true)
public class Food extends BaseEntity {

    @Id
    private String id;

    private String name;

    private String meal;

    private String group;

    private String recipe;

    private String tips;

    @Field(name = "image_urls")
    private List<String> imageUrls;

    @Field(name = "video")
    private String video;

    @Field(name = "more_info")
    private String moreInfo;

    private List<String> labels;

    @Field(name = "food_ingredients")
    private List<FoodIngredient> foodIngredients;

    private List<Nutrient> nutrients;

    @Field(name = "total_calories")
    private Integer totalCalories;

}
