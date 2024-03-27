package com.greethy.nutrition.infra.persistent.mongodb.adapter.evaluate.pal;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.nutrition.core.port.out.evaluate.pal.DeletePalEvaluatePort;
import com.greethy.nutrition.infra.persistent.mongodb.PalEvaluateRepository;
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
