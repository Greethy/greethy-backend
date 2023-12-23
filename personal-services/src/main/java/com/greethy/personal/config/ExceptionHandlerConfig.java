package com.greethy.personal.config;

import com.greethy.personal.application.rest.controller.GlobalReactiveExceptionHandler;
import com.greethy.personal.domain.exception.DuplicateUniqueFieldException;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.ServerCodecConfigurer;

import java.util.Map;

@Configuration
@AutoConfiguration
public class ExceptionHandlerConfig {

    @Bean
    public GlobalReactiveExceptionHandler globalExceptionHandler(WebProperties webProperties,
                                                                 ApplicationContext applicationContext,
                                                                 ServerCodecConfigurer configurer) {
        GlobalReactiveExceptionHandler exceptionHandler = new GlobalReactiveExceptionHandler(
                new DefaultErrorAttributes(),
                webProperties.getResources(),
                applicationContext,
                exceptionStatusCodesMap(),
                HttpStatus.INTERNAL_SERVER_ERROR);

        exceptionHandler.setMessageWriters(configurer.getWriters());
        exceptionHandler.setMessageReaders(configurer.getReaders());

        return exceptionHandler;
    }

    @Bean
    public Map<Class<? extends Exception>, HttpStatus> exceptionStatusCodesMap() {
        return Map.of(
             DuplicateUniqueFieldException.class, HttpStatus.BAD_REQUEST
        );
    }

    @Bean
    public ServerCodecConfigurer serverCodecConfigurer() {
        return ServerCodecConfigurer.create();
    }

}
