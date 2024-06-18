package com.greethy.nutritioncommon.entity.value;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Builder
public class FoodMenu {

    private String foodId;

    private String name;

    @Field(name = "image_url")
    private List<String> imageUrl;

}
