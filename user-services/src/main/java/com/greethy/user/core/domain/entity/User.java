package com.greethy.user.core.domain.entity;

import com.greethy.core.domain.event.UserBodySpecsAddedEvent;
import com.greethy.user.core.domain.event.*;
import com.greethy.user.core.domain.exception.DuplicateUniqueFieldException;
import com.greethy.user.core.domain.exception.NotFoundException;
import com.greethy.user.core.domain.value.PersonalDetail;
import com.greethy.user.core.domain.value.Premium;
import com.greethy.user.core.domain.value.Role;
import com.greethy.user.core.port.in.command.DeleteUserCommand;
import com.greethy.user.core.port.in.command.RegisterUserCommand;
import com.greethy.user.core.port.in.command.UpdateUserCommand;
import com.greethy.user.core.port.out.user.CheckIfExistsUserPort;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * This class represent for user, including login information,
 * personal information, body specified, and referenced ids to other documents.
 *
 * @author Kien N.Thanh
 */
@Data
@Aggregate
@NoArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    @AggregateIdentifier
    private String id;

    @Indexed(unique = true)
    private String username;

    @Indexed(unique = true)
    private String email;

    private Boolean verified = Boolean.FALSE;

    private String password;

    private String avatar;

    @Field(name = "banner_image")
    private String bannerImage;

    private String bio;

    @Field(name = "personal_info")
    private PersonalDetail personalDetail;

    private Premium premium;

    @AggregateMember
    @DocumentReference
    private Networking networking;

    private List<String> roles;

    private List<String> labels;

    @Field(name = "created_at")
    private LocalDateTime createdAt;

    @Field(name = "updated_at")
    private LocalDateTime updatedAt;

    @Field(name = "body_specs_ids")
    private List<String> bodySpecsIds = new ArrayList<>();

    @CommandHandler
    public User(RegisterUserCommand command, CheckIfExistsUserPort port, PasswordEncoder encoder) {
        if (port.existsByUsernameOrEmail(command.getUsername(), command.getEmail())) {
            throw new DuplicateUniqueFieldException();
        }

        var encodedPassword = encoder.encode(command.getPassword());
        var roles = Collections.singletonList(Role.ROLE_USER.getType());
        var networking = new Networking(UUID.randomUUID().toString());

        AggregateLifecycle.apply(UserRegisteredEvent.builder()
                        .userId(command.getUserId())
                        .username(command.getUsername())
                        .email(command.getEmail())
                        .password(encodedPassword)
                        .roles(roles)
                        .personalDetail(new PersonalDetail())
                        .networking(networking)
                        .createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now())
                        .build())
                .andThenApply(() -> NetworkingCreatedEvent.builder()
                        .networking(networking)
                        .build())
                .andThenApplyIf(() -> !this.verified, VerificationEmailSentEvent::new);
    }

    @EventSourcingHandler
    void on(UserRegisteredEvent event) {
        this.id = event.getUserId();
        this.username = event.getUsername();
        this.email = event.getEmail();
        this.password = event.getPassword();
        this.roles = event.getRoles();
        this.personalDetail = event.getPersonalDetail();
        this.networking = event.getNetworking();
        this.createdAt = event.getCreatedAt();
        this.updatedAt = event.getUpdatedAt();
    }

    @EventSourcingHandler
    void on(VerificationEmailSentEvent event) {
        this.verified = Boolean.TRUE;
    }

    @CommandHandler
    void handle(UpdateUserCommand command,
                CheckIfExistsUserPort checkIfExistsUserPort) {
        if (!checkIfExistsUserPort.existsById(command.getUserId())) {
            throw new NotFoundException();
        }

        AggregateLifecycle.apply(UserUpdatedEvent.builder()
                .userId(command.getUserId())
                .avatar(command.getAvatar())
                .bannerImage(command.getBannerImage())
                .bio(command.getBio())
                .personalDetail(command.getPersonalDetail())
                .build()
        );
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

    @EventSourcingHandler
    void handle(UserBodySpecsAddedEvent event) {
        this.bodySpecsIds.add(event.getBodySpecsId());
    }

    @CommandHandler
    void handle(DeleteUserCommand command,
                CheckIfExistsUserPort checkIfExistsUserPort) {
        if (!checkIfExistsUserPort.existsById(command.getUserId())) {
            throw new NotFoundException();
        }
        AggregateLifecycle.apply(UserDeletedEvent.builder()
                .userId(command.getUserId())
                .build());
    }

    @EventSourcingHandler
    void on(UserDeletedEvent event) {
        AggregateLifecycle.markDeleted();
    }

}
