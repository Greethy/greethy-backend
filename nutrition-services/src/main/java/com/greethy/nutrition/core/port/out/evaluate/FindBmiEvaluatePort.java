package com.greethy.nutrition.core.port.out.evaluate;

import com.greethy.nutrition.core.domain.entity.evaluate.BmiEvaluate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FindBmiEvaluatePort {

    Flux<BmiEvaluate> findAll();

    Mono<BmiEvaluate> findByIndexInRange(Double bmiIndex);
}
