package com.greethy.account.auth.domain.entity;

import com.greethy.account.auth.application.rest.dto.command.RegisterUserCommand;
import com.greethy.account.auth.domain.entity.valueobject.Credential;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractAggregateRoot<User> {

    private String id;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private boolean enabled;
    private boolean emailVerified;
    private List<Credential> credentials;

    public User(@Valid RegisterUserCommand command) {
        this.id = UUID.randomUUID().toString();
        this.email = command.email();
        this.username = command.username();
        this.enabled = true;
        this.emailVerified = true;
        this.credentials = List.of(new Credential(command.password()));
    }

}
