package com.greethy.gateway.core.exception;

import com.greethy.core.domain.exception.BaseException;
import lombok.Getter;

@Getter
public class InternalServerException extends BaseException {

    public InternalServerException(Integer status, String message) {
        super(status, message);
    }
}
