package com.greethy.user.core.domain.aggregate;

import com.greethy.user.core.domain.entity.PersonalDetail;
import com.greethy.user.core.domain.entity.Premium;
import com.greethy.user.core.domain.exception.DuplicateUniqueFieldException;
import com.greethy.user.core.event.UserDeletedEvent;
import com.greethy.user.core.event.UserRegisteredEvent;
import com.greethy.user.core.event.UserUpdatedEvent;
import com.greethy.user.core.event.VerificationEmailSentEvent;
import com.greethy.user.core.port.in.command.DeleteUserCommand;
import com.greethy.user.core.port.in.command.RegisterUserCommand;
import com.greethy.user.core.port.in.command.UpdateUserCommand;
import com.greethy.user.core.port.out.CheckIfExistsUserPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 *
 *
 * @author KienThanh
 * */
@Getter
@Aggregate()
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

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @CommandHandler
    UserAggregate(RegisterUserCommand command,
                  CheckIfExistsUserPort port) {
        if(port.existsByUsernameOrEmail(command.getUsername(), command.getEmail())) {
            throw new DuplicateUniqueFieldException(HttpStatus.BAD_REQUEST.value(), "Username or email already used !");
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
    void on(UserRegisteredEvent event) {
        this.id = event.getUserId();
        this.username = event.getUsername();
        this.email = event.getEmail();
        this.password = event.getPassword();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @EventSourcingHandler
    void on(VerificationEmailSentEvent event) {
        this.verified = Boolean.TRUE;
    }

    @CommandHandler
    void handle(UpdateUserCommand command,
                CheckIfExistsUserPort checkIfExistsUserPort) {
        if (!checkIfExistsUserPort.existsById(command.getUserId())) {
            throw new IllegalArgumentException();
        }
        var event = UserUpdatedEvent.builder()
                .userId(command.getUserId())
                .avatar(command.getAvatar())
                .bannerImage(command.getBannerImage())
                .bio(command.getBio())
                .personalDetail(command.getPersonalDetail())
                .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    void on(UserUpdatedEvent event) {
        this.id = event.getUserId();
        this.avatar = event.getAvatar();
        this.bannerImage = event.getBannerImage();
        this.bio = event.getBio();
        this.personalDetail = event.getPersonalDetail();
        this.updatedAt = LocalDateTime.now();
    }

    @CommandHandler
    void handle(DeleteUserCommand command,
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
    void on(UserDeletedEvent event) {
        AggregateLifecycle.markDeleted();
    }

}
