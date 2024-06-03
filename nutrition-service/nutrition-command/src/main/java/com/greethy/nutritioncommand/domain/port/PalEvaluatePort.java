package com.greethy.nutritioncommand.domain.port;

import com.greethy.nutritioncommon.entity.PalEvaluate;
import reactor.core.publisher.Mono;

public interface PalEvaluatePort {

    Mono<PalEvaluate> findByAgeGroup(int age);

}
