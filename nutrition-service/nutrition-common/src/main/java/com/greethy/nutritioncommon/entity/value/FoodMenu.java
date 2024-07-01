package com.greethy.nutritioncommon.entity.value;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodMenu {

    @Field(name = "food_id")
    private String foodId;

    private String name;

    private Double calories;

    @Field(name = "image_url")
    private List<String> imageUrl;

    private Boolean status;

}
