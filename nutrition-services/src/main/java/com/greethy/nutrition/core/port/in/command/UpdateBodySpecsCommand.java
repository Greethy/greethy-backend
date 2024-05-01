package com.greethy.nutrition.core.port.in.command;

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

    private Integer gender;

    private String activityType;
}
