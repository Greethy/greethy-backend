package com.greethy.nutritionquery.domain.port;

import com.greethy.common.api.response.ObjectIdResponse;
import reactor.core.publisher.Mono;

public interface UserClientPort {

    Mono<ObjectIdResponse> getUserIdByUsername(String username);

}
