package com.greethy.nutrition.core.port.out.evaluate;

import com.greethy.nutrition.core.domain.entity.evaluate.BmiEvaluate;
import reactor.core.publisher.Flux;

public interface FindBmiEvaluatePort {

    Flux<BmiEvaluate> findAll();

}
