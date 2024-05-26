package com.greethy.nutrition.core.port.in.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class CreateMenuCommand {

    @TargetAggregateIdentifier
    private String menuId;

    private String userId;

    private String name;

}
