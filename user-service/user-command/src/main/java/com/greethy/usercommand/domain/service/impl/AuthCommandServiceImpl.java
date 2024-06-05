package com.greethy.usercommand.domain.service.impl;

import com.greethy.core.infra.component.i18n.Translator;
import com.greethy.core.infra.component.jwt.JwtTokenProvider;
import com.greethy.usercommand.domain.port.NetworkingPort;
import com.greethy.usercommand.domain.port.RolePort;
import com.greethy.usercommand.domain.port.UserPort;
import com.greethy.usercommand.domain.service.AuthCommandService;
import com.greethy.usercommon.constant.Constant;
import com.greethy.usercommon.dto.request.command.RegisterUserCommand;
import com.greethy.usercommon.dto.request.command.UserLoginCommand;
import com.greethy.usercommon.dto.response.AuthResponse;
import com.greethy.usercommon.entity.Networking;
import com.greethy.usercommon.entity.User;
import com.greethy.usercommon.exception.DuplicateUniqueFieldException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.Collections;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthCommandServiceImpl implements AuthCommandService {

    private final ModelMapper mapper;

    private final Translator translator;

    private final PasswordEncoder encoder;

    private final JwtTokenProvider tokenProvider;

    private final ReactiveAuthenticationManager authenticationManager;

    private final RolePort mongoRolePort;

    private final UserPort mongoUserPort;

    private final NetworkingPort mongoNetworkingPort;

    @Override
    public Mono<AuthResponse> authenticate(UserLoginCommand command) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(
                command.usernameOrEmail(),
                command.password());
        return authenticationManager.authenticate(authenticationToken)
                .map(authentication -> {
                    String accessToken = tokenProvider.createToken(authentication);
                    String message = translator.getLocalizedMessage(Constant.MessageKeys.LOGIN_SUCCESS);
                    return AuthResponse.builder()
                            .message(message)
                            .tokenType("Bearer")
                            .accessToken(accessToken)
                            .build();
                });
    }

    @Override
    public Mono<AuthResponse> registerGreethyUser(RegisterUserCommand registerUserCommand) {
        return mongoUserPort.existsByUsernameOrEmail(registerUserCommand.getUsername(), registerUserCommand.getEmail())
                .filter(isExisted -> !isExisted)
                .switchIfEmpty(Mono.error(() -> {
                    String exceptionMessage = translator.getLocalizedMessage(Constant.MessageKeys.EMAIL_DUPLICATE);
                    return new DuplicateUniqueFieldException(exceptionMessage);
                })).then(Mono.just(registerUserCommand))
                .map(command -> mapper.map(command, User.class))
                .zipWith(mongoRolePort.getRoleByDefaultIsTrue())
                .doOnNext(tuple -> {
                    User user = tuple.getT1();
                    String encodedPassword = encoder.encode(user.getPassword());
                    user.setRoles(Collections.singletonList(tuple.getT2()));
                    user.setPassword(encodedPassword);
                }).map(Tuple2::getT1)
                .flatMap(user -> {
                    var networking = new Networking(UUID.randomUUID().toString());
                    user.setNetworkingId(networking.getId());
                    return Mono.when(
                            mongoUserPort.save(user),
                            mongoNetworkingPort.save(networking)
                    ).thenReturn(user);
                }).doOnSuccess(user -> log.info("User {} saved to MongoDB", user.getUsername()))
                .map(user -> {
                    String accessToken = tokenProvider.createToken(user.getUsername());
                    String message = translator.getLocalizedMessage(Constant.MessageKeys.REGISTER_SUCCESS);
                    return AuthResponse.builder()
                            .message(message)
                            .tokenType("Bearer")
                            .accessToken(accessToken)
                            .build();
                });
    }
}
