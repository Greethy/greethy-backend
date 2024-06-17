package com.greethy.nutritionquery.infra.adapter.client;

import com.greethy.common.api.response.ObjectIdResponse;
import com.greethy.common.infra.component.annotation.DrivenAdapter;
import com.greethy.nutritionquery.domain.port.UserClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@DrivenAdapter
@RequiredArgsConstructor
public class UserClientAdapter implements UserClientPort {


    @Override
    public Mono<ObjectIdResponse> getUserIdByUsername(String username) {
        return WebClient.builder()
                .baseUrl("http://localhost:8182")
                .build()
                .get()
                .uri(builder -> builder.path("identity/ids/" + username).build())
                .retrieve()
                .bodyToMono(ObjectIdResponse.class);
    }
}
