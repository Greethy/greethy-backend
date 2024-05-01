package com.greethy.nutrition.core.port.out.write;

import com.greethy.nutrition.core.domain.entity.BodySpecs;

import reactor.core.publisher.Mono;

public interface SaveBodySpecsPort {

    Mono<BodySpecs> save(BodySpecs bodySpecs);
}
