package com.greethy.nutrition.core.port.in.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Builder
@AllArgsConstructor
public class DeleteBodySpecsCommand {

    @TargetAggregateIdentifier
    private String bodySpecsId;

    private String userId;

}
