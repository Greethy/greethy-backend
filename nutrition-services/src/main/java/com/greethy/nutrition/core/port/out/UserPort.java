package com.greethy.nutrition.core.port.out;

import com.greethy.nutrition.core.domain.value.User;

import reactor.core.publisher.Mono;

public interface UserPort {

    Mono<User> getById(String userId);
}
