package com.greethy.user.core.port.out;

import com.greethy.user.infrastructure.entity.User;
import reactor.core.publisher.Mono;

public interface CreateUserPort {

    Mono<User> create(User user);

}
