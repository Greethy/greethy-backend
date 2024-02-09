package com.greethy.personal.core.port.outbound;

import com.greethy.personal.core.domain.entity.User;
import reactor.core.publisher.Mono;

public interface CreateUserPort {

    Mono<User> create(User user);
}
