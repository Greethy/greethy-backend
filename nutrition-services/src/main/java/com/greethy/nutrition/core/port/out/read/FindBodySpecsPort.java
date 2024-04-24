package com.greethy.nutrition.core.port.out.read;

import com.greethy.nutrition.core.domain.entity.BodySpecs;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FindBodySpecsPort {

    Mono<Long> countAll();

    Mono<BodySpecs> findById(String id);

    Flux<BodySpecs> findAll();

    Flux<BodySpecs> findAllBy(Pageable pageable);

    Flux<BodySpecs> findAllByIds(Flux<String> ids);

}
