package com.greethy.user.core.port.out.user;

import com.greethy.user.core.domain.entity.User;
import reactor.core.publisher.Mono;

public interface SaveUserPort {

    Mono<User> save(User user);

}
