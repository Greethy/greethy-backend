package com.greethy.nutrition.core.port.in.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@AllArgsConstructor
public class DeleteBodySpecsCommand {

    @TargetAggregateIdentifier
    private String bodySpecsId;

}
