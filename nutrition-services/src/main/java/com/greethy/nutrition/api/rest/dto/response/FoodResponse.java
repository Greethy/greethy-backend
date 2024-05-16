package com.greethy.nutrition.api.rest.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.greethy.nutrition.core.domain.entity.FoodIngredient;
import com.greethy.nutrition.core.domain.value.Nutrient;
import com.greethy.nutrition.core.domain.value.Owner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FoodResponse {

    private String id;

    private String name;

    private String group;

    private String meal;

    private String description;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("video_url")
    private String videoUrl;

    @JsonProperty("total_calories")
    private Double totalCalories;

    private String recipe;

    private String tips;

    private String locale;

    private Boolean open;

    private Owner owner;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    private List<FoodIngredient> ingredients;

    private Nutrient nutrient;
}
