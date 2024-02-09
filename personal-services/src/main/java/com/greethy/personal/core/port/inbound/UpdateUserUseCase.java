package com.greethy.personal.core.port.inbound;

import com.greethy.personal.core.domain.entity.User;
import reactor.core.publisher.Mono;

public interface UpdateUserUseCase {

    Mono<User> update(String id, User user);

}
