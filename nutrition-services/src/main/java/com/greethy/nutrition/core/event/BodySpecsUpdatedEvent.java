package com.greethy.nutrition.core.event;

import com.greethy.nutrition.core.domain.entity.specs.Bmi;
import com.greethy.nutrition.core.domain.entity.specs.Bmr;
import com.greethy.nutrition.core.domain.entity.specs.Pal;
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
