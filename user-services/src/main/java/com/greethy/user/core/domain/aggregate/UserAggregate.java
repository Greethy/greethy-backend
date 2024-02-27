package com.greethy.user.core.domain.aggregate;

import com.greethy.user.core.domain.entity.Premium;
import com.greethy.user.core.domain.exception.DuplicateUniqueFieldException;
import com.greethy.user.core.event.UserDeletedEvent;
import com.greethy.user.core.event.UserProfileUpdatedEvent;
import com.greethy.user.core.event.UserRegisteredEvent;
import com.greethy.user.core.event.VerificationEmailSentEvent;
import com.greethy.user.core.port.in.command.DeleteUserCommand;
import com.greethy.user.core.port.in.command.RegisterUserCommand;
import com.greethy.user.core.port.in.command.UpdatePersonalDetailCommand;
import com.greethy.user.core.port.out.CheckIfExistsUserPort;
import com.greethy.user.core.domain.entity.PersonalDetail;

import lombok.Getter;
import lombok.NoArgsConstructor;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDateTime;

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

    private String avatar;

    private String bannerImage;

    private String bio;

    private PersonalDetail personalDetail;

    private Premium premium;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

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
        AggregateLifecycle.apply(event)
                .andThenApplyIf(() -> !this.verified, VerificationEmailSentEvent::new);
    }

    @EventSourcingHandler
    public void on(UserRegisteredEvent event) {
        this.id = event.getUserId();
        this.username = event.getUsername();
        this.email = event.getEmail();
        this.password = event.getPassword();
        this.createdDate = LocalDateTime.now();
    }

    @EventSourcingHandler
    public void on(VerificationEmailSentEvent event) {
        this.verified = Boolean.TRUE;
    }

    @CommandHandler
    public void handle(UpdatePersonalDetailCommand command,
                       CheckIfExistsUserPort checkIfExistsUserPort) {
        if(checkIfExistsUserPort.existsById(command.getUserId())) {
            throw new IllegalArgumentException();
        }
        var event = UserProfileUpdatedEvent.builder()
                .userId(command.getUserId())
                .profile(command.getPersonalDetail())
                .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UserProfileUpdatedEvent event){
        this.id = event.getUserId();
        this.personalDetail = event.getProfile();
        this.updatedDate = LocalDateTime.now();
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
