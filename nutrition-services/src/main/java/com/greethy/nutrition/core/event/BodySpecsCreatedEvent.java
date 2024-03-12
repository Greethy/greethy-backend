package com.greethy.nutrition.core.event;

import com.greethy.nutrition.core.domain.entity.specs.Bmi;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BodySpecsCreatedEvent {

    private String bodySpecsId;

    private Integer age;

    private Double height;

    private Double weight;

    private Bmi bmi;

}
