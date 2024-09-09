package com.greethy.nutritioncommon.dto.value;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class FoodMenuDto {

    @JsonProperty("food_id")
    private String foodId;

    private String name;

    private Double calories;

    @JsonProperty("image_url")
    private List<String> imageUrl;

}
