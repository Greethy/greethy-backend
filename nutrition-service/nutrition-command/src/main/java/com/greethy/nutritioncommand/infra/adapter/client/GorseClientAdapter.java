package com.greethy.nutritioncommand.infra.adapter.client;

import com.greethy.common.infra.component.annotation.DrivenAdapter;
import com.greethy.nutritioncommand.domain.port.GorseClientPort;
import com.greethy.nutritioncommon.dto.response.GorseResponse;
import com.greethy.nutritioncommon.entity.GorseItem;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@DrivenAdapter
@RequiredArgsConstructor
public class GorseClientAdapter implements GorseClientPort {


    @Override
    public Mono<GorseResponse> saveItem(GorseItem item) {
        return WebClient.builder()
                .baseUrl("http://localhost:8087")
                .build()
                .post()
                .uri(uriBuilder -> uriBuilder.path("/api/item").build())
                .bodyValue(item)
                .retrieve()
                .bodyToMono(GorseResponse.class);
    }
}
