package com.greethy.userquery.domain.port;

import com.greethy.usercommon.entity.User;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserPort {

    Mono<User> findById(String id);

    Mono<User> findByUsernameOrEmail(String usernameOrEmail);

    Flux<User> findAll();

    Flux<User> findByPagination(Pageable pageable);

}
