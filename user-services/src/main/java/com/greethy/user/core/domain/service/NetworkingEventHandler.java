package com.greethy.user.core.domain.service;

import com.greethy.user.core.domain.event.NetworkingCreatedEvent;
import com.greethy.user.core.port.out.networking.SaveNetworkingPort;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class NetworkingEventHandler {

    private final SaveNetworkingPort networkingPort;

    @EventHandler
    void on(NetworkingCreatedEvent event) {
        Mono.just(event)
                .map(NetworkingCreatedEvent::getNetworking)
                .flatMap(networkingPort::save)
                .subscribe();
    }

}
