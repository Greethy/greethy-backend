package com.greethy.nutrition.api.rest.dto.request;

import lombok.Data;

@Data
public class CreateBodySpecsRequest {

    private String age;

    private Double height;

    private Double weight;

}
