package com.greethy.feedbackquery.infra.adapter;

import com.greethy.common.infra.component.annotation.DrivenAdapter;
import com.greethy.feedbackcommon.dto.response.ObjectIdResponse;
import com.greethy.feedbackquery.domain.port.NutritionClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@DrivenAdapter
@RequiredArgsConstructor
public class NutritionClientAdapter implements NutritionClientPort {
    @Override
    public Flux<ObjectIdResponse> getAllFoodIds() {
        return WebClient.builder().baseUrl("http://localhost:8184")
                .build()
                .get()
                .uri(builder -> builder.path("/internal/foods/ids").build())
                .retrieve()
                .bodyToFlux(ObjectIdResponse.class);
    }
}
