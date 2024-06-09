package com.greethy.usercommand.domain.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AddedToUserEventConsumer {

    private final ReactiveKafkaConsumerTemplate<String, Object> consumerTemplate;

    @KafkaListener(topics = "${spring.kafka.consumer.topics[0]}")
    private void consumeAddToUserEvent() {
        consumerTemplate.receiveAutoAck()
                .doOnNext(consumerRecord -> log.info("key = {}, value = {}, topic = {}, offset = {}",
                        consumerRecord.key(),
                        consumerRecord.value(),
                        consumerRecord.topic(),
                        consumerRecord.offset()))
                .map(ConsumerRecord::value)
                .doOnError(throwable -> log.error("something bad happened while consuming : {}", throwable.getMessage()))
                .subscribe();
    }

}
