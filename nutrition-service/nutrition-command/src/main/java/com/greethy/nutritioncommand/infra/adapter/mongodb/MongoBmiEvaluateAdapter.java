package com.greethy.nutritioncommand.infra.adapter.mongodb;

import com.greethy.annotation.hexagonal.DrivenAdapter;
import com.greethy.nutritioncommand.domain.port.BmiEvaluatePort;
import com.greethy.nutritioncommon.entity.BmiEvaluate;
import com.greethy.nutritioncommon.repository.BmiEvaluateRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@DrivenAdapter
@RequiredArgsConstructor
public class MongoBmiEvaluateAdapter implements BmiEvaluatePort {

    private final BmiEvaluateRepository repository;


    @Override
    public Mono<BmiEvaluate> findByIndexInRange(Double bmiIndex) {
        return repository.findByIndexInRange(bmiIndex);
    }
}
