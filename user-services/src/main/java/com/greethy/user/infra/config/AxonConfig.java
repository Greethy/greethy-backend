package com.greethy.user.infra.config;

import com.greethy.user.api.error.interceptor.ExceptionWrappingHandlerInterceptor;
import org.axonframework.commandhandling.CommandBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Autowired
    public void commandBus(CommandBus commandBus,
                           ExceptionWrappingHandlerInterceptor exceptionWrappingHandlerInterceptor) {
        
        commandBus.registerHandlerInterceptor(exceptionWrappingHandlerInterceptor);
    }

}
