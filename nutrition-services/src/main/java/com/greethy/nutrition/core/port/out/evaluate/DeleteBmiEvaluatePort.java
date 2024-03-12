package com.greethy.nutrition.core.port.out.evaluate;

import reactor.core.publisher.Mono;

public interface DeleteBmiEvaluatePort {

    Mono<Void> deleteAll();

}
