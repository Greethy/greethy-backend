package com.greethy.nutrition.api.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateBodySpecsRequest {

    private Integer age;

    private Double height;

    private Double weight;

    private Integer gender;

    @JsonProperty("activity_type")
    private String activityType;

}
