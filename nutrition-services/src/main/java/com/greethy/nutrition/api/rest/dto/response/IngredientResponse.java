package com.greethy.nutrition.api.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greethy.nutrition.core.domain.entity.Nutrient;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class IngredientResponse {

    private String name;

    private String code;

    private String locale;

    private String category;

    private Integer edible;

    private Integer calories;

    @JsonProperty("nutrition_per_100g")
    private List<Nutrient> nutrients;

}
