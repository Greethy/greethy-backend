package com.greethy.nutrition.core.port.in.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Data;

@Data
public class CreateFoodCommand {

    @TargetAggregateIdentifier
    private String foodId;

    private String userId;

    private String name;

    private String description;

    private String recipe;

    private String tips;

    private Boolean open;

    private String imageUrl;

    private String videoUrl;
}
