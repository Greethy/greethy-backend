package com.greethy.feedbackquery.domain.port;

import com.greethy.common.api.response.ObjectIdResponse;
import reactor.core.publisher.Flux;

public interface UserClientPort {

    Flux<ObjectIdResponse> getAllUserIds();

}
