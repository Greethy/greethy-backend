package com.greethy.nutrition.infra.persistent.mongodb.adapter.evaluate.pal;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.nutrition.core.domain.entity.evaluate.PalEvaluate;
import com.greethy.nutrition.core.port.out.evaluate.pal.FindPalEvaluatePort;
import com.greethy.nutrition.infra.persistent.mongodb.PalEvaluateRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@InfrastructureAdapter
@RequiredArgsConstructor
public class MongoFindPalEvaluateAdapter implements FindPalEvaluatePort {

    private final PalEvaluateRepository repository;

    @Override
    public Mono<PalEvaluate> findByAgeGroup(int age) {
        return repository.findByAgeGroup(age);
    }
}
