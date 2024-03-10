package com.greethy.user.api.error.interceptor;

import com.greethy.user.api.error.DomainErrorDetail;
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
public class CommandExceptionWrappingInterceptor implements MessageHandlerInterceptor<CommandMessage<?>> {

    @Override
    public Object handle(@Nonnull UnitOfWork<? extends CommandMessage<?>> unitOfWork,
                         @Nonnull InterceptorChain interceptorChain) {
        try {
            return interceptorChain.proceed();
        } catch (Throwable throwable) {
            throw new CommandExecutionException(throwable.getMessage(), throwable, exceptionDetails(throwable));
        }
    }

    private DomainErrorDetail exceptionDetails(Throwable throwable) {
        if(throwable instanceof DuplicateUniqueFieldException exception) {
            return DomainErrorDetail.builder()
                    .name(exception.getClass().getName())
                    .status(exception.getStatus())
                    .message(exception.getMessage())
                    .build();
        }
        return DomainErrorDetail.builder()
                .name(throwable.getClass().getName())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(throwable.getMessage())
                .build();
    }


}
