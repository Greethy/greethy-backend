package com.greethy.personal.domain.port.inbound;

import com.greethy.personal.domain.entity.User;
import reactor.core.publisher.Flux;

public interface FindAllUserUseCase {

    Flux<User> findAll();

}
