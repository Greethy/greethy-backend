package com.greethy.user.api.rest.controller.user;

import org.axonframework.commandhandling.CommandExecutionException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

@Order(-2)
@Component
public class UserExceptionEndpointHandler implements WebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable throwable) {
       if (throwable instanceof CommandExecutionException cee) {
          System.out.println(cee.getMessage());
       }
       return Mono.empty();
    }
}
