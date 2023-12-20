package com.greethy.personal.domain.port.outbound;

import com.greethy.personal.domain.entity.User;
import reactor.core.publisher.Mono;

public interface UpdateUserPort {

    Mono<User> update(String id, User user);

}
