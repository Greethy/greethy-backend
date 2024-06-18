package com.greethy.nutritioncommand.domain.service;

import com.greethy.common.api.response.ObjectIdResponse;
import com.greethy.nutritioncommon.dto.request.command.CreateBodySpecCommand;
import com.greethy.nutritioncommon.dto.request.command.UpdateBodySpecCommand;
import com.greethy.nutritioncommon.dto.response.BodySpecResponse;
import reactor.core.publisher.Mono;

public interface BodySpecCommandService {

    Mono<ObjectIdResponse> createBodySpec(String username, CreateBodySpecCommand command);

    Mono<BodySpecResponse> updateBodySpec(String bodySpecId, UpdateBodySpecCommand command);

    Mono<Void> deleteBodySpec(String bodySpecId);

}
