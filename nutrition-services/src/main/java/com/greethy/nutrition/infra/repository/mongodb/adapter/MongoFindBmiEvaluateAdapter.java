package com.greethy.nutrition.infra.repository.mongodb.adapter;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.nutrition.core.domain.value.BmiEvaluate;
import com.greethy.nutrition.core.port.out.read.FindBmiEvaluatePort;
import com.greethy.nutrition.infra.repository.mongodb.BmiEvaluateRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@InfrastructureAdapter
@RequiredArgsConstructor
public class MongoFindBmiEvaluateAdapter implements FindBmiEvaluatePort {

    private final BmiEvaluateRepository bmiEvaluateRepository;

    @Override
    public Flux<BmiEvaluate> findAll() {
        return bmiEvaluateRepository.findAll();
    }

    @Override
    public Mono<BmiEvaluate> findByIndexInRange(Double bmiIndex) {
        return bmiEvaluateRepository.findByIndexInRange(bmiIndex);
    }
}
