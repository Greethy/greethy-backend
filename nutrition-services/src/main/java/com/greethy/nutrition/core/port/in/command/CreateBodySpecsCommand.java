package com.greethy.nutrition.core.port.in.command;


import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

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

}
