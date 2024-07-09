package com.greethy.nutritionquery.infra.adapter.client;

import com.greethy.common.infra.component.annotation.DrivenAdapter;
import com.greethy.nutritioncommon.dto.response.GorseSimilarityResponse;
import com.greethy.nutritionquery.domain.port.GorseClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@DrivenAdapter
@RequiredArgsConstructor
public class GorseClientAdapter implements GorseClientPort {

    @Override
    public Flux<GorseSimilarityResponse> getSimilarityItems(String itemId, String category, Integer size) {
        return WebClient.builder()
                .baseUrl("http://localhost:8087")
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder.path("/api/item/" + itemId + "/neighbors/" + category)
                        .queryParam("n", size)
                        .build())
                .retrieve()
                .bodyToFlux(GorseSimilarityResponse.class);
    }

    @Override
    public Mono<String[]> getFoodRecommendation(String userId, String category, Integer size) {
        return WebClient.builder()
                .baseUrl("http://localhost:8087")
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder.path("/api/recommend/" + userId + "/" + category)
                        .queryParam("n", size).build())
                .retrieve()
                .bodyToMono(String[].class);

    }

}
