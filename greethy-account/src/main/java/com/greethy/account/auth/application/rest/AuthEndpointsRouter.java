package com.greethy.account.auth.application.rest;

import com.greethy.account.auth.application.rest.dto.command.RegisterUserCommand;
import com.greethy.account.auth.application.rest.dto.response.TokenResponse;
import com.greethy.account.auth.domain.service.AuthService;
import com.greethy.account.common.config.component.ObjectValidator;
import com.greethy.account.common.exception.ExceptionHandler;
import com.greethy.account.common.exception.InvalidInputException;
import com.greethy.account.common.exception.KeycloakClientException;
import com.greethy.core.model.response.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
@RequiredArgsConstructor
public class AuthEndpointsRouter {

    private final AuthService authService;
    private final ObjectValidator validator;
    private final ExceptionHandler exceptionHandler;


    @Bean
    public RouterFunction<ServerResponse> authEndpoints() {
        return RouterFunctions.route()
                .path("/v0/auth", builder -> builder
                        .POST("/register", accept(MediaType.APPLICATION_JSON), this::register)
                        .POST("/authorize", accept(MediaType.APPLICATION_JSON), this::authorize)
                        .POST("/logout", accept(MediaType.APPLICATION_JSON), this::logout))
                .onError(KeycloakClientException.class, exceptionHandler::handle)
                .onError(InvalidInputException.class, exceptionHandler::handle)
                .build();
    }

    public Mono<ServerResponse> register(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(RegisterUserCommand.class)
                .doOnNext(validator::validate)
                .flatMap(authService::register)
                .map(response -> DataResponse.<TokenResponse> builder()
                        .data(response).success(true).build())
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

    public Mono<ServerResponse> authorize(ServerRequest request) {
        return request.bodyToMono(RegisterUserCommand.class)
                .doOnNext(validator::validate)
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

    public Mono<ServerResponse> logout(ServerRequest request) {
        return null;
    }

}
