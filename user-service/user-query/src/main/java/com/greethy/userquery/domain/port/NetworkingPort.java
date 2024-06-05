package com.greethy.userquery.domain.port;

import com.greethy.usercommon.entity.Networking;
import reactor.core.publisher.Mono;

public interface NetworkingPort {

    Mono<Networking> findById(String id);

}
