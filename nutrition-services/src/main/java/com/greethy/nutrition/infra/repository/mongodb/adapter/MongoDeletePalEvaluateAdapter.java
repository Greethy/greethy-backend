package com.greethy.nutrition.infra.repository.mongodb.adapter;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.nutrition.core.port.out.write.DeletePalEvaluatePort;
import com.greethy.nutrition.infra.repository.mongodb.PalEvaluateRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@InfrastructureAdapter
@RequiredArgsConstructor
public class MongoDeletePalEvaluateAdapter implements DeletePalEvaluatePort {

    private final PalEvaluateRepository repository;

    @Override
    public Mono<Void> deleteAll() {
        return repository.deleteAll();
    }
}
