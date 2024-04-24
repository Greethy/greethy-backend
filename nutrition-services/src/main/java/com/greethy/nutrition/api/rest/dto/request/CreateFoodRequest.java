package com.greethy.nutrition.api.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateFoodRequest {

    private String name;

    private String description;

    private String recipe;

    private String tips;

    private Boolean open;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("video_url")
    private String videoUrl;


}
