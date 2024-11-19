package com.greethy.account.user.domain.port;

import com.greethy.account.user.domain.entity.User;
import reactor.core.publisher.Mono;

public interface UserDrivenPort {

    Mono<Void> save(User user);

    Mono<Boolean> exists(String usernameOrEmail);

}
