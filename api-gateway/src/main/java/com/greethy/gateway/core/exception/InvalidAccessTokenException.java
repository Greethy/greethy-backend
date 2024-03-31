package com.greethy.gateway.core.exception;

import com.greethy.core.domain.exception.BaseException;
import lombok.Getter;

@Getter
public class InvalidAccessTokenException extends BaseException {

    public InvalidAccessTokenException(Integer status, String message) {
        super(status, message);
    }
}