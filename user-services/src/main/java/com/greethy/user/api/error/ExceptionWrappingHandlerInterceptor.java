package com.greethy.user.api.error;

import com.greethy.user.core.domain.exception.DuplicateUniqueFieldException;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.InterceptorChain;
import org.axonframework.messaging.MessageHandlerInterceptor;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

@Component
public class ExceptionWrappingHandlerInterceptor implements MessageHandlerInterceptor<CommandMessage<?>> {

    @Override
    public Object handle(@Nonnull UnitOfWork<? extends CommandMessage<?>> unitOfWork,
                         @Nonnull InterceptorChain interceptorChain) {
        try {
            return interceptorChain.proceed();
        } catch (Throwable throwable) {
            throw new CommandExecutionException(throwable.getMessage(), throwable, exceptionDetails(throwable));
        }
    }

    private DomainError exceptionDetails(Throwable throwable) {
        if(throwable instanceof DuplicateUniqueFieldException exception) {
            return DomainError.builder()
                    .name(exception.getClass().getName())
                    .status(exception.getStatus())
                    .message(exception.getMessage())
                    .build();
        }
        return DomainError.builder()
                .name(throwable.getClass().getName())
                .status(HttpStatus.BAD_REQUEST)
                .message(throwable.getMessage())
                .build();
    }


}
