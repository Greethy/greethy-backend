package com.greethy.nutrition.infra.repository.mongodb.adapter;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.nutrition.core.domain.value.PalEvaluate;
import com.greethy.nutrition.core.port.out.evaluate.pal.FindPalEvaluatePort;
import com.greethy.nutrition.infra.repository.mongodb.PalEvaluateRepository;
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
