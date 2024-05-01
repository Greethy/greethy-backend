package com.greethy.user.infra.config;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.queryhandling.QueryBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.greethy.user.core.interceptor.CommandExceptionWrappingInterceptor;
import com.greethy.user.core.interceptor.QueryExceptionWrappingInterceptor;

@Configuration
public class AxonConfig {

    @Autowired
    public void commandBus(CommandBus commandBus, CommandExceptionWrappingInterceptor interceptor) {
        commandBus.registerHandlerInterceptor(interceptor);
    }

    @Autowired
    public void queryBus(QueryBus queryBus, QueryExceptionWrappingInterceptor interceptor) {
        queryBus.registerHandlerInterceptor(interceptor);
    }
}
