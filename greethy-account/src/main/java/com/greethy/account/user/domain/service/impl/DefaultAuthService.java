package com.greethy.account.user.domain.service.impl;

import com.greethy.account.user.application.rest.handler.dto.RegisterUserCommand;
import com.greethy.account.user.domain.entity.User;
import com.greethy.account.user.domain.port.UserDrivenPort;
import com.greethy.account.user.domain.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultAuthService implements AuthService {

    private final UserDrivenPort userPort;

    @Override
    public Mono<Void> signup(RegisterUserCommand command) {
        return Mono.just(command)
                .filterWhen(_ -> userPort.exists(command.username()))
                .filterWhen(_ -> userPort.exists(command.password()))
                .map(User::new)

                .then();
    }

}
