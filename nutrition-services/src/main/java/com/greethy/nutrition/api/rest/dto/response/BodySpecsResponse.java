package com.greethy.nutrition.api.rest.dto.response;

import com.greethy.nutrition.core.domain.value.Bmi;
import com.greethy.nutrition.core.domain.value.Bmr;
import com.greethy.nutrition.core.domain.value.Pal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BodySpecsResponse {

    private Integer age;

    private Double height;

    private Double weight;

    private String gender;

    private Bmi bmi;

    private Bmr bmr;

    private Pal pal;

    private Double tdee;
}
