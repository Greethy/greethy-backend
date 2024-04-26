package com.greethy.user.infra.repository.mongodb.adapter;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.user.core.port.out.write.DeleteNetworkingPort;
import com.greethy.user.infra.repository.mongodb.NetworkingRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@InfrastructureAdapter
@RequiredArgsConstructor
public class MongoDeleteNetworkingAdapter implements DeleteNetworkingPort {

    private final NetworkingRepository repository;


    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }
}
