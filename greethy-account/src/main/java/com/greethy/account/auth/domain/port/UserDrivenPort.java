package com.greethy.account.auth.domain.port;

import com.greethy.account.auth.infrastructure.model.KeycloakUser;
import com.greethy.core.port.CrudDrivenPort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserDrivenPort extends CrudDrivenPort<KeycloakUser, String> {

    Mono<Boolean> exists(String usernameOrEmail);

    Mono<Integer> count(String usernameOrEmail);
}
