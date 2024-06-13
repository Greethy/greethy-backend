package com.greethy.nutritioncommand.domain.port.producer;

import com.greethy.common.domain.event.AddBodySpecToUserEvent;

public interface BodySpecEventProducer {

    void produce(AddBodySpecToUserEvent event);

}
