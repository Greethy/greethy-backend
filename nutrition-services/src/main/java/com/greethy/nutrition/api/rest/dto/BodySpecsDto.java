package com.greethy.nutrition.api.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BodySpecsDto {

    private String id;

    private Integer age;

    private Double height;

    private Double weight;

    private String gender;

}
