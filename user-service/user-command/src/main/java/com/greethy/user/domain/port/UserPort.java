package com.greethy.user.domain.port;

import com.greethy.user.common.entity.User;
import reactor.core.publisher.Mono;

public interface UserPort {

    Mono<User> save(User user);

    Mono<Boolean> existsById(String id);

    Mono<User> findById(String id);

    Mono<Boolean> existsByUsernameOrEmail(String username, String email);

}
