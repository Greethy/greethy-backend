package com.greethy.nutritioncommon.dto.request.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greethy.nutritioncommon.entity.enums.ActivityLevel;
import com.greethy.nutritioncommon.entity.enums.NutritionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UpdateBodySpecCommand {

    private Integer age;

    private Double height;

    private Double weight;

    @JsonProperty("activity_level")
    @Schema(type = "string", allowableValues = { "SEDENTARY", "MODERATELY", "VIGOROUS"})
    private ActivityLevel activityLevel;

    @Schema(type = "string")
    private NutritionType goal;
}
