package com.greethy.user.infra.repository.mongodb.adapter;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.user.core.domain.entity.Networking;
import com.greethy.user.core.port.out.networking.SaveNetworkingPort;
import com.greethy.user.infra.repository.mongodb.NetworkingRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@InfrastructureAdapter
public class MongoSaveNetworkingAdapter implements SaveNetworkingPort {

    private final NetworkingRepository networkingRepository;

    @Override
    public Mono<Networking> save(Networking networking) {
        return networkingRepository.save(networking);
    }
}
