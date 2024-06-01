package com.greethy.usercommand.domain.port;

import com.greethy.usercommon.entity.Networking;
import reactor.core.publisher.Mono;

public interface NetworkingPort {

    Mono<Networking> save(Networking networking);

}
