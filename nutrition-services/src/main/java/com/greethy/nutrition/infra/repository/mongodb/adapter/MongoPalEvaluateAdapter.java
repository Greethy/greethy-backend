package com.greethy.nutrition.infra.repository.mongodb.adapter;

import com.greethy.annotation.hexagonal.DrivenAdapter;
import com.greethy.nutrition.core.domain.value.PalEvaluate;
import com.greethy.nutrition.core.port.out.PalEvaluatePort;
import com.greethy.nutrition.infra.repository.mongodb.PalEvaluateRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@DrivenAdapter("mongoPalEvaluateAdapter")
public class MongoPalEvaluateAdapter implements PalEvaluatePort {

    private final PalEvaluateRepository repository;

    @Override
    public Mono<PalEvaluate> findByAgeGroup(int age) {
        return repository.findByAgeGroup(age);
    }

    @Override
    public Mono<Void> deleteAll() {
        return repository.deleteAll();
    }

    @Override
    public Flux<PalEvaluate> saveAll(Iterable<PalEvaluate> palEvaluates) {
        return repository.saveAll(palEvaluates);
    }
}
