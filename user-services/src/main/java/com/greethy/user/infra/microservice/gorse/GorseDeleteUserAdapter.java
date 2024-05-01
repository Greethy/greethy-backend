package com.greethy.user.infra.microservice.gorse;

import org.springframework.web.reactive.function.client.WebClient;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.user.core.domain.value.RowAffected;
import com.greethy.user.core.port.out.write.DeleteUserPort;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@InfrastructureAdapter("gorse-delete-adapter")
public class GorseDeleteUserAdapter implements DeleteUserPort {

    private static final String GORSE_MASTER_HOSTNAME = "http://localhost:8088";

    private final WebClient.Builder webClientBuilder;

    @Override
    public Mono<Void> deleteById(String id) {
        return webClientBuilder
                .baseUrl(GORSE_MASTER_HOSTNAME)
                .build()
                .delete()
                .uri("/api/user/" + id)
                .header("X-API-Key", "")
                .retrieve()
                .bodyToMono(RowAffected.class)
                .then(Mono.empty());
    }
}
