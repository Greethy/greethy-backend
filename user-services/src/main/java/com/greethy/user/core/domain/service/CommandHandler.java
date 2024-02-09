package com.greethy.user.core.domain.service;

import com.greethy.user.api.command.RegisterUserCommand;

public interface CommandHandler {

    void handle(RegisterUserCommand command);

}
