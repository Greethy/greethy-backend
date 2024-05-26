package com.greethy.nutrition.api.rest.controller.handler;

import com.greethy.nutrition.core.domain.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UserEndpointHandler {

    private final WebClient webClient;

    @Value("${app.microservice.user-services.api-path}")
    private String apiPath;

    public UserEndpointHandler(@Qualifier("userClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<ServerResponse> getUser(ServerRequest serverRequest) {
        String userId = serverRequest.pathVariable("user-id");
        return webClient.get()
                .uri(apiPath + "/" + userId)
                .retrieve()
                .bodyToMono(User.class)
                .flatMap(user -> ServerResponse.ok().bodyValue(user));
    }
}
