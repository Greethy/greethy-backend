package com.greethy.user.infra.repository.gorse;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.user.core.domain.entity.User;
import com.greethy.user.core.domain.value.GorseUser;
import com.greethy.user.core.domain.value.RowAffected;
import com.greethy.user.core.port.out.user.SaveUserPort;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@InfrastructureAdapter("gorse-save-adapter")
public class GorseSaveUserAdapter implements SaveUserPort {

    private final ModelMapper mapper;

    private final WebClient.Builder webClientBuilder;

    private static final String GORSE_MASTER_HOSTNAME = "http://localhost:8088";

    @Override
    public Mono<User> save(User user) {
        return webClientBuilder.baseUrl(GORSE_MASTER_HOSTNAME).build()
                .post()
                .uri("/api/user")
                .header("X-API-Key", "")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(mapper.map(user, GorseUser.class)), GorseUser.class)
                .retrieve()
                .bodyToMono(RowAffected.class)
                .map(rowAffected -> user);
    }
}
