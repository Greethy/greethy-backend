package com.greethy.nutrition.infra.repository.mongodb.adapter;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.nutrition.core.port.out.write.DeleteBodySpecsPort;
import com.greethy.nutrition.infra.repository.mongodb.BodySpecsRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@InfrastructureAdapter
@RequiredArgsConstructor
public class MongoDeleteBodySpecsAdapter implements DeleteBodySpecsPort {

    private final BodySpecsRepository repository;

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }
}
