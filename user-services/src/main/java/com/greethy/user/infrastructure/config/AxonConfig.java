package com.greethy.user.infrastructure.config;

import com.greethy.user.core.domain.exception.ExceptionWrappingHandlerInterceptor;
import org.axonframework.commandhandling.CommandBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("command")
@Configuration
public class AxonConfig {

    @Autowired
    void commandBus(CommandBus commandBus, ExceptionWrappingHandlerInterceptor exceptionWrappingHandlerInterceptor) {
        commandBus.registerHandlerInterceptor(exceptionWrappingHandlerInterceptor);
    }


}
