package com.greethy.usercommon.dto.request.command;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserLoginCommand(
        @JsonProperty("username_or_email") String usernameOrEmail,
        String password
) {
}
