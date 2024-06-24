package com.greethy.gateway.core.service.impl;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.greethy.gateway.api.rest.dto.request.RegisterRequest;
import com.greethy.gateway.api.rest.dto.response.CurrentUserResponse;
import com.greethy.gateway.api.rest.dto.response.UserRegisteredResponse;
import com.greethy.gateway.core.service.UserService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final WebClient.Builder webClientBuilder;

    private static final String USER_SERVICES_HOSTNAME = "http://localhost:8085";

    @Override
    public Mono<Boolean> checkIfUserEmailExists(String email) {
        return webClientBuilder
                .build()
                .get()
                .uri(USER_SERVICES_HOSTNAME, uriBuilder -> uriBuilder
                        .path("/api/v1/user-email/exists")
                        .queryParam("email", email)
                        .build())
                .retrieve()
                .bodyToMono(Boolean.class);
    }

    @Override
    public Mono<UserRegisteredResponse> registerGreethyUser(Mono<RegisterRequest> registerRequest) {
        return registerRequest.flatMap(request -> webClientBuilder
                .build()
                .post()
                .uri(
                        USER_SERVICES_HOSTNAME,
                        uriBuilder -> uriBuilder.path("/api/v1/user").build())
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), RegisterRequest.class)
                .retrieve()
                .bodyToMono(UserRegisteredResponse.class)
                .doOnNext(response -> {
                    response.setUsername(request.getUsername());
                    response.setPassword(request.getPassword());
                }));
    }

    @Override
    public Mono<CurrentUserResponse> getCurrentUser(String username) {
        return webClientBuilder
                .build()
                .get()
                .uri(USER_SERVICES_HOSTNAME, uriBuilder -> uriBuilder
                        .path("/api/v1/user")
                        .queryParam("username-or-email", username)
                        .build())
                .retrieve()
                .bodyToMono(CurrentUserResponse.class);
    }
}
