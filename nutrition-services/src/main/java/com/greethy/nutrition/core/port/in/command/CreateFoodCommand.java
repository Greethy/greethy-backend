package com.greethy.nutrition.core.port.in.command;

import com.greethy.nutrition.core.domain.value.enums.Group;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.greethy.nutrition.core.domain.value.enums.Meal;

import lombok.Data;

@Data
public class CreateFoodCommand {

    @TargetAggregateIdentifier
    private String foodId;

    private String userId;

    private String name;

    private Group group;

    private Meal meal;

    private String description;

    private String recipe;

    private String tips;

    private Boolean open;

    private String imageUrl;

    private String videoUrl;
}
