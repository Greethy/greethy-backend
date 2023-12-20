package com.greethy.personal.domain.port.outbound;

import com.greethy.personal.domain.entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FindUserPort {

    Mono<User> findById(String id);

    Flux<User> findAll();

}
