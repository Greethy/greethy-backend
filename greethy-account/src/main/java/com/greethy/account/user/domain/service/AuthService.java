package com.greethy.account.user.domain.service;

import com.greethy.account.user.application.rest.handler.dto.RegisterUserCommand;
import reactor.core.publisher.Mono;

public interface AuthService {

    Mono<Void> signup(RegisterUserCommand command);


}
