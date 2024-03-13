package com.greethy.nutrition.api.rest.controller.specs;

import com.greethy.annotation.reactive.Handler;
import lombok.RequiredArgsConstructor;
import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway;

@Handler
@RequiredArgsConstructor
public class BodySpecsQueriesEndpointHandler {

    private final ReactorQueryGateway reactiveQueryGateway;



}
