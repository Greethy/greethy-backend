package com.greethy.gateway.core.service.impl;

import com.greethy.gateway.api.rest.dto.request.RegisterRequest;
import com.greethy.gateway.api.rest.dto.response.UserRegisteredResponse;
import com.greethy.gateway.core.exception.AccountExistedException;
import com.greethy.gateway.core.exception.InternalServerException;
import com.greethy.gateway.core.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

    private final WebClient.Builder loadBalancedWebClientBuilder;

    private static final String USER_SERVICES_HOSTNAME = "http://user-services";


    public UserServiceImpl(@Qualifier("loadBalanced-WebClient") WebClient.Builder loadBalancedWebClient) {
        this.loadBalancedWebClientBuilder = loadBalancedWebClient;
    }

    @Override
    public Mono<Boolean> checkIfUserEmailExists(String email) {
        return loadBalancedWebClientBuilder.build()
                .get()
                .uri(USER_SERVICES_HOSTNAME,
                        uriBuilder -> uriBuilder.path("/api/v1/user-email/exists")
                        .queryParam("email", email)
                        .build())
                .retrieve()
                .bodyToMono(Boolean.class);
    }

    @Override
    @Transactional
    public Mono<UserRegisteredResponse> registerGreethyUser(Mono<RegisterRequest> registerRequest) {
        return registerRequest.flatMap(request -> loadBalancedWebClientBuilder.build()
                .post()
                .uri(USER_SERVICES_HOSTNAME,
                        uriBuilder -> uriBuilder.path("/api/v1/user").build())
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), RegisterRequest.class)
                .retrieve()
                .onStatus(HttpStatusCode::isError,
                        clientResponse -> switch (clientResponse.statusCode().value()) {
                            case 400 -> Mono.error(new AccountExistedException(clientResponse.statusCode().value(), "Username or email is existed !"));
                            case 500 -> Mono.error(new InternalServerException(clientResponse.statusCode().value(), "This service gone wrong !"));
                            default -> throw new IllegalStateException("Unexpected value: " + clientResponse.statusCode().value());
                        })
                .bodyToMono(UserRegisteredResponse.class)
                .doOnNext(response -> {
                    response.setUsername(request.getUsername());
                    response.setPassword(request.getPassword());
                })
        );
    }

}
