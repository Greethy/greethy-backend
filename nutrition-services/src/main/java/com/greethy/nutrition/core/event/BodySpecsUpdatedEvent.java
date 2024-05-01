package com.greethy.nutrition.core.event;

import com.greethy.nutrition.core.domain.value.Bmi;
import com.greethy.nutrition.core.domain.value.Bmr;
import com.greethy.nutrition.core.domain.value.Pal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BodySpecsUpdatedEvent {

    private String bodySpecsId;

    private Integer age;

    private Double height;

    private Double weight;

    private String gender;

    private Bmi bmi;

    private Bmr bmr;

    private Pal pal;

    private Double tdee;
}
