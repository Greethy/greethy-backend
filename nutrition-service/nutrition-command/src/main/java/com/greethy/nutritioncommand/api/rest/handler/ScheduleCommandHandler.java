package com.greethy.nutritioncommand.api.rest.handler;

import com.greethy.common.infra.component.annotation.EndpointHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@EndpointHandler
@RequiredArgsConstructor
public class ScheduleCommandHandler {

    public Mono<ServerResponse> createSchedule(ServerRequest serverRequest) {
        return null;
    }

}
