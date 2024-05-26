package com.greethy.nutrition.core.port.out;

import com.greethy.nutrition.core.domain.entity.User;
import reactor.core.publisher.Mono;

public interface UserPort {

    Mono<User> getById(String userId);
}
