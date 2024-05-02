package com.greethy.nutrition.core.port.out.read;

import com.greethy.nutrition.core.domain.value.User;

import reactor.core.publisher.Mono;

public interface GetUserPort {

    Mono<User> getById(String userId);
}
