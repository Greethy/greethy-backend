package com.greethy.nutrition.api.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greethy.nutrition.core.domain.value.enums.ActivityLevel;
import com.greethy.nutrition.core.domain.value.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateBodySpecsRequest {

    private Integer age;

    private Double height;

    private Double weight;

    private Gender gender;

    @JsonProperty("activity_level")
    @Schema(type = "string", allowableValues = { "SEDENTARY", "MODERATELY", "VIGOROUS"})
    private ActivityLevel activityLevel;

    private String goal;
}
