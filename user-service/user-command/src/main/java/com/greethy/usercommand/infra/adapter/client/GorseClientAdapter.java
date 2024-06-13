package com.greethy.usercommand.infra.adapter.client;

import com.greethy.common.infra.component.annotation.DrivenAdapter;
import com.greethy.usercommand.domain.port.GorseClientPort;
import com.greethy.usercommon.dto.response.GorseResponse;
import com.greethy.usercommon.entity.GorseUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@DrivenAdapter
@RequiredArgsConstructor
public class GorseClientAdapter implements GorseClientPort {

    @Override
    public Mono<GorseResponse> saveUser(GorseUser gorseUser) {
        return WebClient.builder()
                .baseUrl("http://localhost:8087")
                .build()
                .post()
                .uri(uriBuilder -> uriBuilder.path("/api/user").build())
                .bodyValue(gorseUser)
                .retrieve()
                .bodyToMono(GorseResponse.class);
    }

}
