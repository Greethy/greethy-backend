package com.greethy.user.core.domain.aggregate;

import com.greethy.user.core.domain.exception.DuplicateUniqueFieldException;
import com.greethy.user.core.event.UserDeletedEvent;
import com.greethy.user.core.event.UserProfileUpdatedEvent;
import com.greethy.user.core.event.UserRegisteredEvent;
import com.greethy.user.core.event.VerificationEmailSentEvent;
import com.greethy.user.core.port.in.command.DeleteUserCommand;
import com.greethy.user.core.port.in.command.RegisterUserCommand;
import com.greethy.user.core.port.in.command.UpdateUserProfileCommand;
import com.greethy.user.core.port.out.CheckIfExistsUserPort;
import com.greethy.user.infrastructure.entity.Profile;

import lombok.Getter;
import lombok.NoArgsConstructor;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

/**
 *
 *
 * @author KienThanh
 * */
@Getter
@Aggregate
@NoArgsConstructor
public class UserAggregate {

    @AggregateIdentifier
    private String id;

    private String username;

    private String email;

    private Boolean verified = Boolean.FALSE;

    private String password;

    private Profile profile;

    @CommandHandler
    public UserAggregate(RegisterUserCommand command,
                         CheckIfExistsUserPort checkIfExistsUserPort) {
        if(checkIfExistsUserPort.existsByUsernameOrEmail(command.getUsername(), command.getEmail())) {
            throw new DuplicateUniqueFieldException();
        }
        var event = UserRegisteredEvent.builder()
                .userId(command.getUserId())
                .username(command.getUsername())
                .email(command.getEmail())
                .password(command.getPassword())
                .build();
        AggregateLifecycle.apply(event);
                //.andThenApplyIf(this::getVerified, VerificationEmailSentEvent::new);
    }

    @EventSourcingHandler
    public void on(UserRegisteredEvent event) {
        this.id = event.getUserId();
        this.username = event.getUsername();
        this.email = event.getEmail();
        this.password = event.getPassword();
    }

    @EventSourcingHandler
    public void on(VerificationEmailSentEvent event) {
        this.verified = Boolean.TRUE;
    }

    @CommandHandler
    public void handle(UpdateUserProfileCommand command,
                       CheckIfExistsUserPort checkIfExistsUserPort) {
        if(checkIfExistsUserPort.existsById(command.getUserId())) {
            throw new IllegalArgumentException();
        }
        var event = UserProfileUpdatedEvent.builder()
                .userId(command.getUserId())
                .profile(command.getProfile())
                .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UserProfileUpdatedEvent event){
        this.id = event.getUserId();
        this.profile = event.getProfile();
    }

    @CommandHandler
    public void handle(DeleteUserCommand command,
                       CheckIfExistsUserPort checkIfExistsUserPort) {
        if(!checkIfExistsUserPort.existsById(command.getUserId())) {
            throw new IllegalArgumentException();
        }
        var event = UserDeletedEvent.builder()
                .userId(command.getUserId())
                .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UserDeletedEvent event) {
        AggregateLifecycle.markDeleted();
    }

}
