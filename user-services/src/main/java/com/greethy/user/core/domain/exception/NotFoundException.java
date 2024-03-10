package com.greethy.user.core.domain.exception;

import com.greethy.core.domain.exception.BaseException;

public class NotFoundException extends BaseException {

    public NotFoundException(Integer status, String message) {
        super(status, message);
    }
}
