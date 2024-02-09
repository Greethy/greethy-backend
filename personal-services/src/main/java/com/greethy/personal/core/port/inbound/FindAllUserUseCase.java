package com.greethy.personal.core.port.inbound;

import com.greethy.personal.core.domain.entity.User;
import reactor.core.publisher.Flux;

public interface FindAllUserUseCase {

    Flux<User> findAll();

}
