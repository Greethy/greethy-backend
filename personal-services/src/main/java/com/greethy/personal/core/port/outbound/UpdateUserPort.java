package com.greethy.personal.core.port.outbound;

import com.greethy.personal.core.domain.entity.User;
import reactor.core.publisher.Mono;

public interface UpdateUserPort {

    Mono<User> update(String id, User user);

}
