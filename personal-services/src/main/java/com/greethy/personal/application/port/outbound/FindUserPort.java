package com.greethy.personal.application.port.outbound;

import com.greethy.personal.application.domain.entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FindUserPort {

    Mono<User> findById(String id);

    Flux<User> findAll();

}
