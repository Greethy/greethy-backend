package com.greethy.user.core.domain.exception;

import com.greethy.user.api.error.UserBusinessError;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.InterceptorChain;
import org.axonframework.messaging.MessageHandlerInterceptor;
import org.axonframework.messaging.unitofwork.UnitOfWork;

import javax.annotation.Nonnull;

@Slf4j
public class ExceptionWrappingHandlerInterceptor implements MessageHandlerInterceptor<CommandMessage<?>> {

    @Override
    public Object handle(@Nonnull UnitOfWork<? extends CommandMessage<?>> unitOfWork,
                         @Nonnull InterceptorChain interceptorChain) {
        try {
            return interceptorChain.proceed();
        } catch (Throwable throwable) {
            return new CommandExecutionException("", throwable, exceptionDetails(throwable));
        }
    }

    private UserBusinessError exceptionDetails(Throwable throwable) {
        if (throwable instanceof UserException userException) {
            var userError = new UserBusinessError(userException.getClass().getName(), userException.getMessage());
            log.info("Converted User's exception to " + userError);
            return userError;
        } else {
            var userError = new UserBusinessError(throwable.getClass().getName(), throwable.getMessage());
            log.info("Converted User's exception to " + userError);
            return userError;
        }

    }

}
