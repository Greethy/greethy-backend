package com.greethy.user.core.port.out;

import reactor.core.publisher.Mono;

public interface CheckIfExistsUserPort {

    Mono<Boolean> existsById(String id);

    Boolean existsByUsernameOrEmail(String username, String email);

}
