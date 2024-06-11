package com.greethy.usercommand.domain.consumer;

import com.greethy.common.domain.event.AddToUserEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Component;
import reactor.util.retry.Retry;

import java.time.Duration;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventConsumer {

    private final ReactiveKafkaConsumerTemplate<String, AddToUserEvent> consumerTemplate;

    @EventListener(ApplicationStartedEvent.class)
    public void startConsume() {
        consumerTemplate.receiveAutoAck()
                .doOnNext(consumerRecord -> log.info("key = {}, value = {}, topic = {}, offset = {}",
                        consumerRecord.key(),
                        consumerRecord.value(),
                        consumerRecord.topic(),
                        consumerRecord.offset())
                ).map(ConsumerRecord::value)
                .doOnError(error -> log.error("Error receiving event, will retry", error))
                .retryWhen(Retry.fixedDelay(Long.MAX_VALUE, Duration.ofSeconds(10)))
                .subscribe(object -> log.info("event: {}", object));
    }



}
