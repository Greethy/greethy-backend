package com.greethy.nutritioncommand.domain.port;

import com.greethy.nutritioncommon.entity.BodySpec;
import reactor.core.publisher.Mono;

public interface BodySpecPort {

    Mono<BodySpec> save(BodySpec bodySpec);

}
