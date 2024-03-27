package com.greethy.nutrition.core.port.out.evaluate.pal;

import com.greethy.nutrition.core.domain.entity.evaluate.PalEvaluate;
import reactor.core.publisher.Mono;

public interface FindPalEvaluatePort {

    Mono<PalEvaluate> findByAgeGroup(int age);

}
