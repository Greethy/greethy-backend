package com.greethy.personal.domain.port.outbound;

import reactor.core.publisher.Mono;

public interface ExistsUserPort {

    Mono<Boolean> existsById(String id);

    Mono<Boolean> existsByUsernameOrEmail(String username, String email);

}
