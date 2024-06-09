package com.greethy.nutritioncommand.domain.port.producer;

import com.greethy.nutritioncommand.domain.event.AddToUserEvent;
import com.greethy.nutritioncommand.domain.event.BodySpecCreatedEvent;
import reactor.core.publisher.Mono;

public interface BodySpecEventProducer {

    Mono<Void> produce(AddToUserEvent event);

    Mono<Void> produce(BodySpecCreatedEvent event);

}
