package com.greethy.nutritioncommand.domain.port.producer;

import com.greethy.common.domain.event.AddToUserEvent;
import com.greethy.nutritioncommand.domain.event.BodySpecCreatedEvent;
import reactor.core.publisher.Mono;

public interface BodySpecEventProducer {

    void produce(AddToUserEvent event);

    Mono<Void> produce(BodySpecCreatedEvent event);

}
