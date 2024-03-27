package com.greethy.nutrition.core.port.out.evaluate.pal;

import com.greethy.nutrition.core.domain.entity.evaluate.PalEvaluate;
import reactor.core.publisher.Flux;

public interface SavePalEvaluatePort {

    Flux<PalEvaluate> saveAll(Iterable<PalEvaluate> palEvaluates);

}
