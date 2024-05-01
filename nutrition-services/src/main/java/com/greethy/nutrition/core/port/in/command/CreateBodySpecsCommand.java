package com.greethy.nutrition.core.port.in.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Data;

@Data
public class CreateBodySpecsCommand {

    @TargetAggregateIdentifier
    private String bodySpecsId;

    private String userId;

    private Integer age;

    private Double height;

    private Double weight;

    private Integer gender;

    private String activityType;

    private String goal;
}
