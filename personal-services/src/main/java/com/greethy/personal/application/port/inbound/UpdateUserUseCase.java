package com.greethy.personal.application.port.inbound;

import com.greethy.personal.application.domain.entity.User;
import reactor.core.publisher.Mono;

public interface UpdateUserUseCase {

    Mono<User> update(String id, User user);

}
