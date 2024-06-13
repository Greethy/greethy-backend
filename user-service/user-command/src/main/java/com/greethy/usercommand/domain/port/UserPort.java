package com.greethy.usercommand.domain.port;

import com.greethy.usercommon.entity.User;
import reactor.core.publisher.Mono;

public interface UserPort {

    Mono<User> save(User user);

    Mono<Boolean> existsById(String id);

    Mono<User> findById(String id);

    Mono<User> findByUsername(String username);

    Mono<Boolean> existsByUsernameOrEmail(String username, String email);

}
