package com.greethy.user.core.port.in.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
@AllArgsConstructor
public class DeleteUserCommand {

    @TargetAggregateIdentifier
    private String userId;

}
