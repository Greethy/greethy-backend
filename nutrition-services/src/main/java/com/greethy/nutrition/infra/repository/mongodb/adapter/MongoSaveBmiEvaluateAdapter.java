package com.greethy.nutrition.infra.repository.mongodb.adapter;

import com.greethy.annotation.hexagonal.InfrastructureAdapter;
import com.greethy.nutrition.core.domain.value.BmiEvaluate;
import com.greethy.nutrition.core.port.out.write.SaveBmiEvaluatePort;
import com.greethy.nutrition.infra.repository.mongodb.BmiEvaluateRepository;
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
