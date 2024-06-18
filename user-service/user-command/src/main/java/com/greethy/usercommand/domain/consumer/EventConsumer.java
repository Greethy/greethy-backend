package com.greethy.usercommand.domain.consumer;

import com.greethy.common.domain.event.AddBodySpecToUserEvent;
import com.greethy.common.infra.util.DataUtil;
import com.greethy.usercommand.domain.port.BodySpecsManagementPort;
import com.greethy.usercommand.domain.port.UserPort;
import com.greethy.usercommon.entity.BodySpecsManagement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventConsumer {

    private final UserPort userPort;

    private final BodySpecsManagementPort bodySpecsManagementPort;

    private final ReactiveKafkaConsumerTemplate<String, AddBodySpecToUserEvent> consumerTemplate;

    @EventListener(ApplicationStartedEvent.class)
    public void consume() {
        consumerTemplate.receiveAutoAck()
                .doOnError(error -> log.error("Error receiving event, will retry", error))
                .retryWhen(Retry.fixedDelay(Long.MAX_VALUE, Duration.ofSeconds(10)))
                .map(ConsumerRecord::value)
                .flatMap(this::processEvent)
                .subscribe(event -> log.info("event: {}", event));
    }

    private Mono<?> processEvent(AddBodySpecToUserEvent event) {
        var payload = event.getPayload();
        return userPort.findByUsername(payload.username())
                .switchIfEmpty(Mono.error(() -> new Exception("")))
                .flatMap(user -> {
                    if (DataUtil.isNullOrEmpty(user.getBodySpecsManagementId())) {
                        return bodySpecsManagementPort.save(new BodySpecsManagement())
                                .doOnNext(bodySpecsManagement -> user.setBodySpecsManagementId(bodySpecsManagement.getId()))
                                .flatMap(bodySpecsManagement -> userPort.save(user))
                                .thenReturn(user);
                    }
                    return Mono.just(user);
                }).flatMap(user -> bodySpecsManagementPort.findById(user.getBodySpecsManagementId())
                        .doOnNext(bodySpecsManagement -> {
                            bodySpecsManagement.setUserId(user.getId());
                            bodySpecsManagement.setPresentBodySpecId(payload.bodySpecId());
                            bodySpecsManagement.getBodySpecIds().add(payload.bodySpecId());
                        }).flatMap(bodySpecsManagementPort::save)
                ).thenReturn(event);

    }


}
