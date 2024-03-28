package com.greethy.nutrition.api.rest.dto.response;

import com.greethy.nutrition.core.domain.entity.specs.Bmi;
import com.greethy.nutrition.core.domain.entity.specs.Bmr;
import com.greethy.nutrition.core.domain.entity.specs.Pal;
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
