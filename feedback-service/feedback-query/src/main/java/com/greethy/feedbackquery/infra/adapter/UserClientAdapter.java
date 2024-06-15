package com.greethy.feedbackquery.infra.adapter;

import com.greethy.common.infra.component.annotation.DrivenAdapter;
import com.greethy.feedbackcommon.dto.response.ObjectIdResponse;
import com.greethy.feedbackquery.domain.port.UserClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@DrivenAdapter
@RequiredArgsConstructor
public class UserClientAdapter implements UserClientPort {
    @Override
    public Flux<ObjectIdResponse> getAllUserIds() {
        return WebClient.builder().baseUrl("http://localhost:8182")
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder.path("/internal/ids").build())
                .retrieve()
                .bodyToFlux(ObjectIdResponse.class);
    }

}
