package com.greethy.account.user.domain.entity;

import com.greethy.account.user.application.rest.handler.dto.RegisterUserCommand;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class User {

    private String id;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
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
