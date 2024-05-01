package com.greethy.nutrition.core.port.out.write;

import com.greethy.nutrition.core.domain.value.PalEvaluate;

import reactor.core.publisher.Flux;

public interface SavePalEvaluatePort {

    Flux<PalEvaluate> saveAll(Iterable<PalEvaluate> palEvaluates);
}
