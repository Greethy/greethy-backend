package com.greethy.nutrition.core.port.out.read;

import com.greethy.nutrition.core.domain.value.PalEvaluate;

import reactor.core.publisher.Mono;

public interface FindPalEvaluatePort {

    Mono<PalEvaluate> findByAgeGroup(int age);
}
