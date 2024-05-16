package com.greethy.nutrition.core.port.out.write;

import reactor.core.publisher.Mono;

public interface DeleteFoodPort {

    Mono<Void> deleteAll();

}
