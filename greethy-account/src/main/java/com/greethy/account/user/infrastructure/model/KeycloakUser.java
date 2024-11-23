package com.greethy.account.user.infrastructure.model;

import com.greethy.account.user.domain.entity.Credential;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class KeycloakUser {

    private String id;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private boolean enabled;
    private boolean emailVerified;
    private List<Credential> credentials;

}
