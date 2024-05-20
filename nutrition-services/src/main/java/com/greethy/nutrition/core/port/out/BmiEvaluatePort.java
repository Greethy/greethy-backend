package com.greethy.nutrition.core.port.out;

import com.greethy.nutrition.core.domain.value.BmiEvaluate;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BmiEvaluatePort {

    Mono<BmiEvaluate> save(BmiEvaluate bmiEvaluate);

    Mono<BmiEvaluate> findByIndexInRange(Double bmiIndex);

    Mono<Void> deleteAll();

    Flux<BmiEvaluate> saveAll(Iterable<BmiEvaluate> bmiEvaluates);

}
