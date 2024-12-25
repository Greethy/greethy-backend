package com.greethy.account.auth.domain.service.impl;

import com.greethy.account.auth.application.rest.dto.command.RegisterUserCommand;
import com.greethy.account.auth.application.rest.dto.response.TokenResponse;
import com.greethy.account.auth.domain.entity.User;
import com.greethy.account.common.event.UserRegisteredEvent;
import com.greethy.account.auth.domain.port.TokenDrivenPort;
import com.greethy.account.auth.domain.port.UserDrivenPort;
import com.greethy.account.auth.domain.service.AuthService;
import com.greethy.account.auth.infrastructure.mapper.UserMapper;
import com.greethy.account.common.config.component.ObjectValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultAuthService implements AuthService {

    private final UserMapper userMapper;
    private final UserDrivenPort userPort;
    private final TokenDrivenPort tokenPort;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Mono<TokenResponse> authorize() {
        return null;
    }

    @Override
    public Mono<TokenResponse> register(RegisterUserCommand command) {
        return Mono
                .just(command)
                .map(User::new)
                .map(userMapper::toModel)
                .flatMap(keycloakUser -> userPort.save(keycloakUser)
                        .flatMap(userId -> {
                            if (StringUtils.hasText(userId)) {
                                return Mono.fromRunnable(() -> {
                                    var event = UserRegisteredEvent.builder().id(userId)
                                            .email(keycloakUser.getEmail()).username(keycloakUser.getUsername())
                                            .enabled(keycloakUser.isEnabled()).emailVerified(keycloakUser.isEmailVerified())
                                            .build();
                                    eventPublisher.publishEvent(event);
                                }).then(tokenPort.getToken(command.username(), command.password()));
                            }
                            return Mono.error(new RuntimeException());
                        })
                ).map(keycloakToken -> TokenResponse.builder()
                        .tokenType(keycloakToken.getTokenType())
                        .accessToken(keycloakToken.getAccessToken())
                        .refreshToken(keycloakToken.getRefreshToken())
                        .refreshExpiresIn(keycloakToken.getRefreshExpiresIn())
                        .expiresIn(keycloakToken.getExpiresIn()).build()
                );


    }

}
