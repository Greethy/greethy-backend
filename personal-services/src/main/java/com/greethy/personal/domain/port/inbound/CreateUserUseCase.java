package com.greethy.personal.domain.port.inbound;

import com.greethy.personal.domain.entity.User;
import reactor.core.publisher.Mono;

public interface CreateUserUseCase {

    Mono<User> createUser(User user);

}
