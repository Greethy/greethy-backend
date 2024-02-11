package com.greethy.user.core.port.in.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class RegisterUserCommand {

    @TargetAggregateIdentifier
    private String userId;

    private String username;

    private String email;

    private String password;
}
