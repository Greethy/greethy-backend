package com.greethy.nutrition.core.port.out.evaluate.pal;

import reactor.core.publisher.Mono;

public interface DeletePalEvaluatePort {

    Mono<Void> deleteAll();

}
