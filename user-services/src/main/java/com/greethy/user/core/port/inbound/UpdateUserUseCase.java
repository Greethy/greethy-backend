package com.greethy.user.core.port.inbound;

import com.greethy.user.core.domain.entity.User;
import reactor.core.publisher.Mono;

public interface UpdateUserUseCase {

    Mono<User> update(String id, User user);

}
