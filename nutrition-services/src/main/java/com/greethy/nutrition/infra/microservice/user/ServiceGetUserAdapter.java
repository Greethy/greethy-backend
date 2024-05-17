package com.greethy.nutrition.infra.microservice.user;

import org.springframework.web.reactive.function.client.WebClient;

import com.greethy.annotation.hexagonal.DrivenAdapter;
import com.greethy.nutrition.core.domain.value.User;
import com.greethy.nutrition.core.port.out.UserPort;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@DrivenAdapter
@RequiredArgsConstructor
public class ServiceGetUserAdapter implements UserPort {

    private final WebClient.Builder webClient;

    @Override
    public Mono<User> getById(String userId) {
        return webClient
                .baseUrl("http://localhost:8085")
                .build()
                .get()
                .uri("/api/v1/users/" + userId)
                .retrieve()
                .bodyToMono(User.class);
    }
}
