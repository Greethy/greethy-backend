package com.greethy.user.infra.config;

import com.greethy.user.core.interceptor.CommandExceptionWrappingInterceptor;
import org.axonframework.commandhandling.CommandBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Autowired
    public void commandBus(CommandBus commandBus,
                           CommandExceptionWrappingInterceptor commandExceptionWrappingInterceptor) {
        commandBus.registerHandlerInterceptor(commandExceptionWrappingInterceptor);
    }

}
