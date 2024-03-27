package com.greethy.nutrition.core.port.out.evaluate.bmi;

import com.greethy.nutrition.core.domain.entity.evaluate.BmiEvaluate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SaveBmiEvaluatePort {

    Mono<BmiEvaluate> save(BmiEvaluate bmiEvaluate);

    Flux<BmiEvaluate> saveAll(Iterable<BmiEvaluate> bmiEvaluates);
}
