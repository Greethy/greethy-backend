package com.greethy.nutrition.core.port.out;

import com.greethy.nutrition.core.domain.value.PalEvaluate;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PalEvaluatePort {

    Mono<PalEvaluate> findByAgeGroup(int age);

    Mono<Void> deleteAll();

    Flux<PalEvaluate> saveAll(Iterable<PalEvaluate> palEvaluates);

}
