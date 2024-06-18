package com.greethy.nutritioncommand.domain.service;

import com.greethy.common.api.response.ObjectIdResponse;
import reactor.core.publisher.Mono;

public interface MenuCommandService {

    Mono<ObjectIdResponse> createArrangedMenu(String username);

}
