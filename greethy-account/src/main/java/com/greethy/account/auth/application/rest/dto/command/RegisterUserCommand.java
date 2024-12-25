package com.greethy.account.auth.application.rest.dto.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterUserCommand(

        @NotBlank(message = "user.username.not-blank")
        String username,

        @NotBlank(message = "user.email.not-blank")
        @Email(message = "user.email.email-pattern")
        String email,

        @NotBlank(message = "user.first-name.not-blank")
        @JsonProperty(value = "first_name")
        String firstName,

        @NotBlank(message = "user.last-name.not-blank")
        @JsonProperty(value = "last_name")
        String lastName,

        @NotBlank(message = "user.password.not-blank")
        String password

) {}
