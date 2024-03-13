package com.greethy.nutrition.infra.persistent.mongodb.adapter.evaluate;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.nutrition.core.domain.entity.evaluate.BmiEvaluate;
import com.greethy.nutrition.core.port.out.evaluate.SaveBmiEvaluatePort;
import com.greethy.nutrition.infra.persistent.mongodb.BmiEvaluateRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@InfrastructureAdapter
@RequiredArgsConstructor
public class MongoSaveBmiEvaluateAdapter implements SaveBmiEvaluatePort {

    private final BmiEvaluateRepository bmiEvaluateRepository;

    @Override
    public Mono<BmiEvaluate> save(BmiEvaluate bmiEvaluate) {
        return bmiEvaluateRepository.save(bmiEvaluate);
    }

    @Override
    public Flux<BmiEvaluate> saveAll(Iterable<BmiEvaluate> bmiEvaluates) {
        return bmiEvaluateRepository.saveAll(bmiEvaluates);
    }

}
