package com.greethy.nutrition.core.port.in.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DeleteBodySpecsCommand {

    @TargetAggregateIdentifier
    private String bodySpecsId;

    private String userId;
}
