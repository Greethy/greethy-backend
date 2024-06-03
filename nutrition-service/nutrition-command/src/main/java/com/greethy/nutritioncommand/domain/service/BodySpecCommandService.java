package com.greethy.nutritioncommand.domain.service;

import com.greethy.nutritioncommon.dto.request.CreateBodySpecCommand;
import com.greethy.nutritioncommon.dto.response.BodySpecResponse;
import reactor.core.publisher.Mono;

public interface BodySpecCommandService {

    Mono<BodySpecResponse> createBodySpec(CreateBodySpecCommand command);

}
