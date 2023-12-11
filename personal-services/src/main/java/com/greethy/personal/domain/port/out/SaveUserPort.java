package com.greethy.personal.domain.port.out;

import com.greethy.personal.domain.entity.User;
import reactor.core.publisher.Mono;

public interface SaveUserPort {

    Mono<User> save(User user);
}
