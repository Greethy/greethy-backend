package com.greethy.userquery.domain.port;

import com.greethy.usercommon.entity.User;
import reactor.core.publisher.Mono;

public interface UserPort {

    Mono<User> findById(String id);

}
