package com.greethy.usercommon.dto.request.command;

import com.greethy.usercommon.constant.Constants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterUserCommand {

    @NotBlank(message = Constants.MessageKeys.DATA_USERNAME_OR_EMAIL_BLANK)
    private String username;

    @Email(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$", message = Constants.MessageKeys.DATA_EMAIL_INVALID_PATTERN)
    @NotBlank(message = Constants.MessageKeys.DATA_USERNAME_OR_EMAIL_BLANK)
    private String email;

    @NotBlank(message = Constants.MessageKeys.DATA_PASSWORD_BLANK)
    private String password;

}
