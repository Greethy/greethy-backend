package com.greethy.nutrition.infra.persistent.mongodb.adapter.evaluate.pal;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.nutrition.core.domain.entity.evaluate.PalEvaluate;
import com.greethy.nutrition.core.port.out.evaluate.pal.SavePalEvaluatePort;
import com.greethy.nutrition.infra.persistent.mongodb.PalEvaluateRepository;
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
