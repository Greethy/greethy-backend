package com.greethy.nutrition.core.port.out.write;

import com.greethy.nutrition.core.domain.value.BmiEvaluate;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SaveBmiEvaluatePort {

    Mono<BmiEvaluate> save(BmiEvaluate bmiEvaluate);

    Flux<BmiEvaluate> saveAll(Iterable<BmiEvaluate> bmiEvaluates);
}
