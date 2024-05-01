package com.greethy.user.core.port.out.write;

import reactor.core.publisher.Mono;

public interface DeleteUserPort {

    Mono<Void> deleteById(String id);
}
