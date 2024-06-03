package com.greethy.nutritioncommon.dto.value;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PalDto {


    @JsonProperty("activity_level")
    private String activityLevel;

    private Double value;

}
