package com.greethy.userquery.infra.adapter.mongodb;

import com.greethy.common.infra.component.annotation.DrivenAdapter;
import com.greethy.usercommon.entity.Networking;
import com.greethy.usercommon.repository.mongodb.NetworkingRepository;
import com.greethy.userquery.domain.port.NetworkingPort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@DrivenAdapter
@RequiredArgsConstructor
public class MongoNetworkingAdapter implements NetworkingPort {

    private final NetworkingRepository repository;

    @Override
    public Mono<Networking> findById(String id) {
        return repository.findById(id);
    }
}
