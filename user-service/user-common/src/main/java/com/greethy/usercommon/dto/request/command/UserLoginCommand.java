package com.greethy.usercommon.dto.request.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greethy.usercommon.constant.Constants;
import jakarta.validation.constraints.NotBlank;

public record UserLoginCommand(
        @JsonProperty("username_or_email")
        @NotBlank(message = Constants.MessageKeys.DATA_USERNAME_OR_EMAIL_BLANK) String usernameOrEmail,
        @NotBlank(message = Constants.MessageKeys.DATA_PASSWORD_BLANK) String password
) {
}
