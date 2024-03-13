package com.greethy.nutrition.core.port.out.specs;

import com.greethy.nutrition.core.domain.entity.specs.BodySpecs;
import reactor.core.publisher.Mono;

public interface SaveBodySpecsPort {

    Mono<BodySpecs> save(BodySpecs bodySpecs);

}
