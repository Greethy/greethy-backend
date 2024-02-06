package com.greethy.personal.application.port.inbound;

import com.greethy.personal.application.domain.entity.User;
import reactor.core.publisher.Flux;

public interface FindAllUserUseCase {

    Flux<User> findAll();

}
