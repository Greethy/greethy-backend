package com.greethy.nutritioncommand.infra.adapter.kafka;

import com.greethy.common.infra.component.annotation.DrivenAdapter;
import com.greethy.common.domain.event.AddBodySpecToUserEvent;
import com.greethy.nutritioncommand.domain.port.producer.BodySpecEventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;

@Slf4j
@DrivenAdapter
@RequiredArgsConstructor
public class BodySpecEventProducerAdapter implements BodySpecEventProducer {

    @Value("${spring.kafka.topic}")
    private String topicName;

    private final ReactiveKafkaProducerTemplate<String, Object> producerTemplate;

    @Override
    public void produce(AddBodySpecToUserEvent event) {
        producerTemplate.send(topicName, event.getId(), event)
                .doOnSuccess(senderResult -> log.info("sent {} offset : {}", event, senderResult.recordMetadata().offset()))
                .subscribe();
    }

}
