package com.greethy.core.domain.exception;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {

    private final Integer status;

    public BaseException(Integer status, String message) {
        super(message);
        this.status = status;
    }

}
