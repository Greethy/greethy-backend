package com.greethy.account.auth.domain.port;

import com.greethy.account.auth.infrastructure.model.KeycloakToken;
import reactor.core.publisher.Mono;

public interface TokenDrivenPort {

    Mono<KeycloakToken> getToken(String usernameOrEmail, String password);

}
