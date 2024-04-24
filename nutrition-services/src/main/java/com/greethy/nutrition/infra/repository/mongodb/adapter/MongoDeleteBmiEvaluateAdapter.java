package com.greethy.nutrition.infra.repository.mongodb.adapter;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.nutrition.core.port.out.write.DeleteBmiEvaluatePort;
import com.greethy.nutrition.infra.repository.mongodb.BmiEvaluateRepository;
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
