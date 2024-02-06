package com.greethy.personal.application.port.outbound;

import com.greethy.personal.application.domain.entity.User;
import reactor.core.publisher.Mono;

public interface UpdateUserPort {

    Mono<User> update(String id, User user);

}
