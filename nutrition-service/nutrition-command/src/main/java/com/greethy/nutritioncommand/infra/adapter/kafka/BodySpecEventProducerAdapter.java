package com.greethy.nutritioncommand.infra.adapter.kafka;

import com.greethy.common.infra.component.annotation.DrivenAdapter;
import com.greethy.nutritioncommand.domain.event.AddToUserEvent;
import com.greethy.nutritioncommand.domain.event.BodySpecCreatedEvent;
import com.greethy.nutritioncommand.domain.port.producer.BodySpecEventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import reactor.core.publisher.Mono;

@Slf4j
@DrivenAdapter
@RequiredArgsConstructor
public class BodySpecEventProducerAdapter implements BodySpecEventProducer {

    @Value("${spring.kafka.topic}")
    private String topicName;

    private final ReactiveKafkaProducerTemplate<String, Object> producerTemplate;

    @Override
    public Mono<Void> produce(AddToUserEvent event) {
        return producerTemplate.send(topicName, event)
                .then();
    }

    @Override
    public Mono<Void> produce(BodySpecCreatedEvent event) {
        return null;
    }
}
