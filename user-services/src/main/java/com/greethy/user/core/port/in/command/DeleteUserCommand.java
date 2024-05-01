package com.greethy.user.core.port.in.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DeleteUserCommand {

    @TargetAggregateIdentifier
    private String userId;
}
