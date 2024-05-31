package com.greethy.user.infra.adapter.mongodb;

import com.greethy.annotation.hexagonal.DrivenAdapter;
import com.greethy.user.domain.port.NetworkingPort;
import com.greethy.user.common.entity.Networking;
import com.greethy.user.common.repository.NetworkingRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@DrivenAdapter
@RequiredArgsConstructor
public class MongoNetworkingAdapter implements NetworkingPort {

    private final NetworkingRepository repository;

    @Override
    public Mono<Networking> save(Networking networking) {
        return repository.save(networking);
    }
}
