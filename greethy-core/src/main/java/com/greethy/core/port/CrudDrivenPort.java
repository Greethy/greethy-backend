package com.greethy.core.port;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CrudDrivenPort<T, ID> {

    Mono<Void> save(T t);

    Mono<T> findById(ID id);

    Flux<T> findAllBy(Integer pageNumber, Integer pageSize);

}
