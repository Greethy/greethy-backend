package com.greethy.nutritioncommon.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GorseSimilarityResponse {

    @JsonProperty("Id")
    private String id;

    @JsonProperty("Score")
    private double score;

}
