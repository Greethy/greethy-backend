package com.greethy.user.core.port.out;

import com.greethy.user.infrastructure.entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FindUserPort {

    Mono<User> findById(String id);

    Flux<User> findAll();

}
