package com.greethy.nutrition.core.port.in.command;

import com.greethy.nutrition.core.domain.value.enums.ActivityLevel;
import com.greethy.nutrition.core.domain.value.enums.Gender;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateBodySpecsCommand {

    @TargetAggregateIdentifier
    private String bodySpecsId;

    private Integer age;

    private Double height;

    private Double weight;

    private Gender gender;

    private ActivityLevel activityLevel;
}
