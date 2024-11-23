package com.greethy.account.user.domain.port;

import com.greethy.account.user.infrastructure.model.KeycloakUser;
import com.greethy.core.port.CrudDrivenPort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserDrivenPort extends CrudDrivenPort<KeycloakUser, String> {

    Flux<KeycloakUser> getUsers();

    Mono<Boolean> exists(String usernameOrEmail);

}
