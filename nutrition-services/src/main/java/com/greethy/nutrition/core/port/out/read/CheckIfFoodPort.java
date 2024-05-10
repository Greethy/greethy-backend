package com.greethy.nutrition.core.port.out.read;

import reactor.core.publisher.Mono;

public interface CheckIfFoodPort {

    Mono<Boolean> existsById(String id);
}
