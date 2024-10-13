package com.greethy.account.api.rest.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

public record UserLoginCommand(@JsonProperty("username-or-email") String usernameOrEmail,
                               String password) {

    public UserLoginCommand(@NonNull String usernameOrEmail,
                            @NonNull String password) {
        this.usernameOrEmail = usernameOrEmail;
        this.password = password;
    }
}
