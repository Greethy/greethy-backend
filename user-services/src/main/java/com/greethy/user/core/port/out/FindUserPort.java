package com.greethy.user.core.port.out;

import com.greethy.user.core.domain.entity.User;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FindUserPort {

    Mono<User> findById(String id);

    Flux<User> findAll();

    Flux<User> findAll(Pageable pageable);

}
