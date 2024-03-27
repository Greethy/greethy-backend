package com.greethy.nutrition.core.port.out.evaluate.bmi;

import reactor.core.publisher.Mono;

public interface DeleteBmiEvaluatePort {

    Mono<Void> deleteAll();

}
