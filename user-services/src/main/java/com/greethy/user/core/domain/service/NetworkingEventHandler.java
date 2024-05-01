package com.greethy.user.core.domain.service;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

import com.greethy.user.core.event.NetworkingCreatedEvent;
import com.greethy.user.core.event.NetworkingDeletedEvent;
import com.greethy.user.core.port.out.write.DeleteNetworkingPort;
import com.greethy.user.core.port.out.write.SaveNetworkingPort;

import reactor.core.publisher.Mono;

@Service
public class NetworkingEventHandler {

    @EventHandler
    void on(NetworkingCreatedEvent event, SaveNetworkingPort networkingPort) {
        Mono.just(event)
                .map(NetworkingCreatedEvent::getNetworking)
                .flatMap(networkingPort::save)
                .subscribe();
    }

    @EventHandler
    void on(NetworkingDeletedEvent event, DeleteNetworkingPort networkingPort) {
        Mono.just(event)
                .map(NetworkingDeletedEvent::getNetworkingId)
                .flatMap(networkingPort::deleteById)
                .subscribe();
    }
}
