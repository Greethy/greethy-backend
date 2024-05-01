package com.greethy.user.core.interceptor;

import javax.annotation.Nonnull;

import org.axonframework.messaging.InterceptorChain;
import org.axonframework.messaging.MessageHandlerInterceptor;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.axonframework.queryhandling.QueryExecutionException;
import org.axonframework.queryhandling.QueryMessage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.greethy.core.domain.exception.BaseException;
import com.greethy.core.domain.exception.DomainErrorDetail;

@Component
public class QueryExceptionWrappingInterceptor implements MessageHandlerInterceptor<QueryMessage<?, ?>> {

    @Override
    public Object handle(
            @Nonnull UnitOfWork<? extends QueryMessage<?, ?>> unitOfWork, @Nonnull InterceptorChain interceptorChain) {
        try {
            return interceptorChain.proceed();
        } catch (Throwable throwable) {
            throw new QueryExecutionException(throwable.getMessage(), throwable, exceptionDetails(throwable), false);
        }
    }

    private DomainErrorDetail exceptionDetails(Throwable throwable) {
        if (throwable instanceof BaseException exception) {
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
