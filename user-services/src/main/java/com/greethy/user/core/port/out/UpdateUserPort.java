package com.greethy.user.core.port.out;

import com.greethy.user.infrastructure.entity.User;
import reactor.core.publisher.Mono;

public interface UpdateUserPort {

    Mono<User> update(String id, User user);

}
