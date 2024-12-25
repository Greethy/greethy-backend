package com.greethy.account.auth.domain.service;

import com.greethy.account.auth.application.rest.dto.command.RegisterUserCommand;
import com.greethy.account.auth.application.rest.dto.response.TokenResponse;
import reactor.core.publisher.Mono;

public interface AuthService {

    Mono<TokenResponse> authorize();

    Mono<TokenResponse> register(RegisterUserCommand command);

}
