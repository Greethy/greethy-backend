package com.greethy.user.infrastructure.config;

import com.greethy.user.core.domain.exception.ExceptionWrappingHandlerInterceptor;
import org.axonframework.commandhandling.CommandBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Autowired
    void commandBus(CommandBus commandBus) {
        commandBus.registerHandlerInterceptor(new ExceptionWrappingHandlerInterceptor());
    }

}
