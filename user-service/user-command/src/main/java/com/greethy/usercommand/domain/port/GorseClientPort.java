package com.greethy.usercommand.domain.port;

import com.greethy.usercommon.dto.response.GorseResponse;
import com.greethy.usercommon.entity.GorseUser;
import reactor.core.publisher.Mono;

public interface GorseClientPort {

    Mono<GorseResponse> saveUser(GorseUser gorseUser);

}
