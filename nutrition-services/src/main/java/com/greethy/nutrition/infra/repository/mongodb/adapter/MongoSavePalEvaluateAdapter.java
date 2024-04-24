package com.greethy.nutrition.infra.repository.mongodb.adapter;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.nutrition.core.domain.value.PalEvaluate;
import com.greethy.nutrition.core.port.out.evaluate.pal.SavePalEvaluatePort;
import com.greethy.nutrition.infra.repository.mongodb.PalEvaluateRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@InfrastructureAdapter
@RequiredArgsConstructor
public class MongoSavePalEvaluateAdapter implements SavePalEvaluatePort {

    private final PalEvaluateRepository repository;

    @Override
    public Flux<PalEvaluate> saveAll(Iterable<PalEvaluate> palEvaluates) {
        return repository.saveAll(palEvaluates);
    }
}
