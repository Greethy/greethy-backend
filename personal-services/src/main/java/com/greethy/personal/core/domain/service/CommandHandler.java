package com.greethy.personal.core.domain.service;

import com.greethy.personal.api.command.RegisterUserCommand;

public interface CommandHandler {

    void handle(RegisterUserCommand command);

}
