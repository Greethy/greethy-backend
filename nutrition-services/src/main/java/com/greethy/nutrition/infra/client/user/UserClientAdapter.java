package com.greethy.nutrition.infra.client.user;

import com.greethy.annotation.hexagonal.DrivenAdapter;
import com.greethy.nutrition.core.domain.entity.User;
import com.greethy.nutrition.core.port.out.UserPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@DrivenAdapter("userClientAdapter")
public class UserClientAdapter implements UserPort {

    @Value("${app.microservice.user-services.api-path}")
    private String apiPath;

    private final WebClient webClient;

    public UserClientAdapter(@Qualifier("userClient") WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<User> getById(String userId) {
        return webClient.get()
                .uri(apiPath  + "/" + userId)
                .retrieve()
                .bodyToMono(User.class);
    }
}
