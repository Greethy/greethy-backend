package com.greethy.nutrition.infra.persistent.mongodb.adapter;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.nutrition.core.port.out.evaluate.DeleteBmiEvaluatePort;
import com.greethy.nutrition.infra.persistent.mongodb.BmiEvaluateRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@InfrastructureAdapter
@RequiredArgsConstructor
public class MongoDeleteBmiEvaluateAdapter implements DeleteBmiEvaluatePort {

    private final BmiEvaluateRepository bmiEvaluateRepository;

    @Override
    public Mono<Void> deleteAll() {
        return bmiEvaluateRepository.deleteAll();
    }

}