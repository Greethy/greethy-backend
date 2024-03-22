package com.greethy.gateway.core.service.impl;

import com.greethy.gateway.api.rest.dto.request.RegisterRequest;
import com.greethy.gateway.api.rest.dto.response.UserRegisteredResponse;
import com.greethy.gateway.core.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder encoder;

    private final WebClient.Builder loadBalancedWebClientBuilder;

    private static final String USER_SERVICES_HOSTNAME = "http://user-services";


    public UserServiceImpl(PasswordEncoder encoder,
                           @Qualifier("loadBalanced-WebClient") WebClient.Builder loadBalancedWebClient) {
        this.encoder = encoder;
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
    public Mono<UserRegisteredResponse> registerGreethyUser(Mono<RegisterRequest> registerRequest) {
        return registerRequest.doOnNext(request -> {
            String encodedPassword = encoder.encode(request.getPassword());
            request.setPassword(encodedPassword);
        }).flatMap(request -> loadBalancedWebClientBuilder.build()
                .post()
                .uri(USER_SERVICES_HOSTNAME,
                        uriBuilder -> uriBuilder.path("/api/v1/user").build())
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), RegisterRequest.class)
                .retrieve()
                .bodyToMono(UserRegisteredResponse.class)
        );
    }

}
