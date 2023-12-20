package com.greethy.personal.domain.port.inbound;

import com.greethy.personal.domain.entity.User;
import reactor.core.publisher.Mono;

public interface SaveUserUseCase {

    Mono<User> saveUser(User user);

}
