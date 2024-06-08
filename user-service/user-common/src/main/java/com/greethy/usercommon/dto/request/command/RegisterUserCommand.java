package com.greethy.usercommon.dto.request.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterUserCommand {

    @NotBlank(message = "Username cannot be ")
    private String username;

    private String email;

    private String password;

}
