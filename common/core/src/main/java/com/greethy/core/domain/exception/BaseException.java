package com.greethy.core.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class BaseException extends RuntimeException {

    private final HttpStatus status;

    public BaseException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

}
