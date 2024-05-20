package com.greethy.nutrition.infra.repository.mongodb.adapter;

import com.greethy.annotation.hexagonal.DrivenAdapter;
import com.greethy.nutrition.core.domain.value.BmiEvaluate;
import com.greethy.nutrition.core.port.out.BmiEvaluatePort;
import com.greethy.nutrition.infra.repository.mongodb.BmiEvaluateRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@DrivenAdapter("mongoBmiEvaluateAdapter")
public class MongoBmiEvaluateAdapter implements BmiEvaluatePort {

    private final BmiEvaluateRepository repository;

    @Override
    public Mono<BmiEvaluate> save(BmiEvaluate bmiEvaluate) {
        return repository.save(bmiEvaluate);
    }

    @Override
    public Mono<BmiEvaluate> findByIndexInRange(Double bmiIndex) {
        return repository.findByIndexInRange(bmiIndex);
    }

    @Override
    public Mono<Void> deleteAll() {
        return repository.deleteAll();
    }

    @Override
    public Flux<BmiEvaluate> saveAll(Iterable<BmiEvaluate> bmiEvaluates) {
        return repository.saveAll(bmiEvaluates);
    }
}
