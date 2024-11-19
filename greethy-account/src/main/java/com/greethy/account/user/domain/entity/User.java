package com.greethy.account.user.domain.entity;

import com.greethy.account.user.application.rest.handler.dto.RegisterUserCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.List;
import java.util.UUID;


@Data
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractAggregateRoot<User> {

    private String id;
    private String email;
    private String username;
    private boolean enabled;
    private boolean emailVerified;
    private List<Credential> credentials;

    public User(RegisterUserCommand registerUserCommand) {
        this.id = UUID.randomUUID().toString();
        this.email = registerUserCommand.email();
        this.username = registerUserCommand.username();
        this.enabled = true;
        this.emailVerified = false;
        this.credentials = List.of(new Credential("password", registerUserCommand.password(), false));
        
    }

}
