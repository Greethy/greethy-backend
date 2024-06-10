package com.greethy.nutritioncommon.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greethy.nutritioncommon.entity.enums.ActivityLevel;
import com.greethy.nutritioncommon.entity.enums.NutritionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateBodySpecCommand {

    private String username;

    private Integer age;

    private Double height;

    private Double weight;

    @JsonProperty("activity_level")
    @Schema(type = "string", allowableValues = { "SEDENTARY", "MODERATELY", "VIGOROUS"})
    private ActivityLevel activityLevel;

    @Schema
    private NutritionType goal;

}
