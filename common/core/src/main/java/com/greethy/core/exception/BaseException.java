package com.greethy.core.exception;

import lombok.Getter;

@Getter
public abstract class BaseException extends Exception {

    public BaseException(String message) {
        super(message);
    }

}
