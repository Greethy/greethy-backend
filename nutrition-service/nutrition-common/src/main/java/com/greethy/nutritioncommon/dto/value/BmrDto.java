package com.greethy.nutritioncommon.dto.value;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BmrDto {

    @JsonProperty("bmr_per_kg")
    private Double bmrPerKg;

    @JsonProperty("bmr_per_day")
    private Double bmrPerDay;
}
