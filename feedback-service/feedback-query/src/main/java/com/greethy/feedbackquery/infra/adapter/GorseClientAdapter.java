package com.greethy.feedbackquery.infra.adapter;

import com.greethy.common.infra.component.annotation.DrivenAdapter;
import com.greethy.feedbackcommon.dto.response.GorseResponse;
import com.greethy.feedbackcommon.entity.GorseFeedback;
import com.greethy.feedbackquery.domain.port.GorseClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@DrivenAdapter
@RequiredArgsConstructor
public class GorseClientAdapter implements GorseClientPort {


    @Override
    public Mono<GorseResponse> saveFeedbacks(List<GorseFeedback> feedbacks) {
        return WebClient.builder().baseUrl("http://localhost:8087")
                .build()
                .post()
                .uri(builder -> builder.path("/api/feedback").build())
                .bodyValue(feedbacks)
                .retrieve()
                .bodyToMono(GorseResponse.class);
    }
}
