package com.greethy.nutrition.core.port.out.write;

import reactor.core.publisher.Mono;

public interface DeletePalEvaluatePort {

    Mono<Void> deleteAll();

}
