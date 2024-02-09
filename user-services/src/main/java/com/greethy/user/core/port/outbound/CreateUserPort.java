package com.greethy.user.core.port.outbound;

import com.greethy.user.core.domain.entity.User;
import reactor.core.publisher.Mono;

public interface CreateUserPort {

    Mono<User> create(User user);
}
