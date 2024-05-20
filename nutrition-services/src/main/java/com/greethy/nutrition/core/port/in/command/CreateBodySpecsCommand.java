package com.greethy.nutrition.core.port.in.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.greethy.nutrition.core.domain.value.enums.ActivityLevel;
import com.greethy.nutrition.core.domain.value.enums.Gender;

import lombok.Data;

@Data
public class CreateBodySpecsCommand {

    @TargetAggregateIdentifier
    private String bodySpecsId;

    private String userId;

    private Integer age;

    private Double height;

    private Double weight;

    private Gender gender;

    private ActivityLevel activityLevel;

    private String goal;
}
