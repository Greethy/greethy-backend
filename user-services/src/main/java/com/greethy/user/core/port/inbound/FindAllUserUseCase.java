package com.greethy.user.core.port.inbound;

import com.greethy.user.core.domain.entity.User;
import reactor.core.publisher.Flux;

public interface FindAllUserUseCase {

    Flux<User> findAll();

}
