package com.greethy.user.core.port.out.networking;

import com.greethy.user.core.domain.entity.Networking;
import reactor.core.publisher.Mono;

public interface SaveNetworkingPort {

    Mono<Networking> save(Networking networking);

}
