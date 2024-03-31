package com.greethy.nutrition.api.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateBodySpecsRequest {

    private Integer age;

    private Double height;

    private Double weight;

    private Integer gender;

    @JsonProperty("activity_type")
    private String activityType;

}
