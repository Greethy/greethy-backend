package com.greethy.nutritioncommand.domain.port;

import com.greethy.nutritioncommon.entity.BmiEvaluate;
import reactor.core.publisher.Mono;

public interface BmiEvaluatePort {

    Mono<BmiEvaluate> findByIndexInRange(Double bmiIndex);

}
