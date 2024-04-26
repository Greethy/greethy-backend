package com.greethy.user.core.port.out.read;

import reactor.core.publisher.Mono;

public interface CheckIfUserExistsPort {

    Mono<Boolean> existsById(String id);

    Boolean existsByEmail(String email);

    Mono<Boolean> existsByUsernameOrEmail(String username, String email);

}
