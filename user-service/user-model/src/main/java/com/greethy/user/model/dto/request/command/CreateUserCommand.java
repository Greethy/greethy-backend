package com.greethy.user.model.dto.request.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserCommand {

    private String id;

    private String username;

    private String email;

    private String password;

}
