package com.greethy.user.api.command;

import com.greethy.core.message.command.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RegisterUserCommand extends BaseCommand {

    private String username;

    private String email;

}
