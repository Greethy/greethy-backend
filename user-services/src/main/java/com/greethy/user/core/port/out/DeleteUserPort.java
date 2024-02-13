package com.greethy.user.core.port.out;

import com.greethy.user.infrastructure.entity.User;
import reactor.core.publisher.Mono;


public interface DeleteUserPort {

    Mono<Void> delete(User user);
}
