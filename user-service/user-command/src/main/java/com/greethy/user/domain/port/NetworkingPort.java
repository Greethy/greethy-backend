package com.greethy.user.domain.port;

import com.greethy.user.common.entity.Networking;
import reactor.core.publisher.Mono;

public interface NetworkingPort {

    Mono<Networking> save(Networking networking);

}
