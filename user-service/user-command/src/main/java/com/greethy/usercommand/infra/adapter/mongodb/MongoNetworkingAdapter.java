package com.greethy.usercommand.infra.adapter.mongodb;

import com.greethy.annotation.hexagonal.DrivenAdapter;
import com.greethy.usercommand.domain.port.NetworkingPort;
import com.greethy.usercommon.entity.Networking;
import com.greethy.usercommon.repository.mongodb.NetworkingRepository;
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
