package com.greethy.nutritioncommon.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greethy.nutritioncommon.dto.value.BmiDto;
import com.greethy.nutritioncommon.dto.value.BmrDto;
import com.greethy.nutritioncommon.dto.value.PalDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class BodySpecResponse {

    private String id;

    private Double height;

    private Double weight;

    private Integer age;

    private Double tdee;

    private BmiDto bmi;

    private BmrDto bmr;

    private PalDto pal;

    @JsonProperty("activity_level")
    private String activityLevel;

    private String goal;

    @Schema(description = "Timestamp when the user was created", format = "date-time")
    @JsonProperty("created_at")
    private Date createdAt;

    @Schema(description = "Timestamp when the user's information was last updated", format = "date-time")
    @JsonProperty("updated_at")
    private Date updatedAt;
}
