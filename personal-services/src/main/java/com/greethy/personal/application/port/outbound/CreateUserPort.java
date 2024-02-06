package com.greethy.personal.application.port.outbound;

import com.greethy.personal.application.domain.entity.User;
import reactor.core.publisher.Mono;

public interface CreateUserPort {

    Mono<User> create(User user);
}
