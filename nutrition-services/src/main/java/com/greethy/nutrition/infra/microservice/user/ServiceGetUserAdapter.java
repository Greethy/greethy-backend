package com.greethy.nutrition.infra.microservice.user;

import org.springframework.web.reactive.function.client.WebClient;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.nutrition.core.domain.value.Owner;
import com.greethy.nutrition.core.port.out.read.GetUserPort;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@InfrastructureAdapter
@RequiredArgsConstructor
public class ServiceGetUserAdapter implements GetUserPort {

    private final WebClient.Builder webClient;

    @Override
    public Mono<Owner> getById(String userId) {
        return webClient
                .baseUrl("http://localhost:8085")
                .build()
                .get()
                .uri("/api/v1/users/" + userId)
                .retrieve()
                .bodyToMono(Owner.class);
    }
}
