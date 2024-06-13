package com.greethy.common.domain.exception;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {

    protected final Integer status;

    public BaseException(Integer status, String message) {
        super(message);
        this.status = status;
    }
}
