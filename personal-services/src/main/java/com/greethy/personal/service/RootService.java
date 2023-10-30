package com.greethy.personal.service;

import reactor.core.publisher.Mono;

public interface RootService<I, O> {

    Mono<O> save(I i);

}
