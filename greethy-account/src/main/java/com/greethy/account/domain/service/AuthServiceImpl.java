package com.greethy.account.domain.service;

import com.greethy.account.domain.message.command.AuthenticateUser;
import com.greethy.account.application.rest.handler.model.response.LoginResponse;
import com.greethy.account.domain.port.in.AuthService;
import com.greethy.account.infrastructure.adapter.webclient.KeycloakClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final KeycloakClient keycloakClient;


    @Override
    public Mono<LoginResponse> getToken(AuthenticateUser command) {
        return null;
    }
}