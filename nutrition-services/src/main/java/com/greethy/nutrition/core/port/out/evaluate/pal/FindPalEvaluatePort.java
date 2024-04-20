package com.greethy.nutrition.core.port.out.evaluate.pal;

import com.greethy.nutrition.core.domain.value.PalEvaluate;
import reactor.core.publisher.Mono;

public interface FindPalEvaluatePort {

    Mono<PalEvaluate> findByAgeGroup(int age);

}
