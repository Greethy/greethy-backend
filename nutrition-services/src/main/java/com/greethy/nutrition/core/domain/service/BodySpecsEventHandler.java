package com.greethy.nutrition.core.domain.service;

import com.greethy.nutrition.core.domain.entity.BodySpecs;
import com.greethy.nutrition.core.event.BodySpecsCreatedEvent;
import com.greethy.nutrition.core.event.BodySpecsUpdatedEvent;
import com.greethy.nutrition.core.port.out.specs.SaveBodySpecsPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class BodySpecsEventHandler {

    private final ModelMapper mapper;

    private final SaveBodySpecsPort saveBodySpecsPort;

    @EventHandler
    void on(BodySpecsCreatedEvent event) {
        Mono.just(event)
                .map(bodySpecsCreatedEvent -> mapper.map(bodySpecsCreatedEvent, BodySpecs.class))
                //.doOnNext(bodySpecs -> bodySpecs.setCreateAt())
                .flatMap(saveBodySpecsPort::save)
                .subscribe(bodySpecs -> log.info("User's bodySpecs created with id {}", bodySpecs.getId()));
    }

    @EventHandler
    void on(BodySpecsUpdatedEvent event) {
        Mono.just(event)
                .map(bodySpecsUpdatedEvent -> mapper.map(bodySpecsUpdatedEvent, BodySpecs.class))
                .flatMap(saveBodySpecsPort::save)
                .subscribe();
    }

}
