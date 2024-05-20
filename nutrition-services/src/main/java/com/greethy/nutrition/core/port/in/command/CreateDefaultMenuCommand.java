package com.greethy.nutrition.core.port.in.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class CreateDefaultMenuCommand {

    @TargetAggregateIdentifier
    private String menuId;



}