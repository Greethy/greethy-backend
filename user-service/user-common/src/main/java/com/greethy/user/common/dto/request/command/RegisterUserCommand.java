package com.greethy.user.common.dto.request.command;

import lombok.Data;

@Data
public class RegisterUserCommand {

    private String username;

    private String email;

    private String password;

}
