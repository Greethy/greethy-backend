package com.greethy.user.core.domain.aggregate;

import com.greethy.user.core.event.UserRegisteredEvent;
import com.greethy.user.core.port.in.command.RegisterUserCommand;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import org.axonframework.modelling.command.AggregateLifecycle;

@Aggregate
@NoArgsConstructor
@RequiredArgsConstructor
public class UserAggregate {

    @AggregateIdentifier
    private String id;

    private String username;

    private String email;

    @CommandHandler
    public UserAggregate(RegisterUserCommand command) {
        var event = UserRegisteredEvent.builder()
                .userId(command.getUserId())
                .username(command.getUsername())
                .email(command.getEmail())
                .password(command.getPassword())
                .build();
        AggregateLifecycle.apply(event);
    }

}
