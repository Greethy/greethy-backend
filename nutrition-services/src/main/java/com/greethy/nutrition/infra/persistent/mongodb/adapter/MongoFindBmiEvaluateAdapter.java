package com.greethy.nutrition.infra.persistent.mongodb.adapter;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.nutrition.core.domain.entity.evaluate.BmiEvaluate;
import com.greethy.nutrition.core.port.out.evaluate.FindBmiEvaluatePort;
import com.greethy.nutrition.infra.persistent.mongodb.BmiEvaluateRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@InfrastructureAdapter
@RequiredArgsConstructor
public class MongoFindBmiEvaluateAdapter implements FindBmiEvaluatePort {

    private final BmiEvaluateRepository bmiEvaluateRepository;

    @Override
    public Flux<BmiEvaluate> findAll() {
        return bmiEvaluateRepository.findAll();
    }
}
