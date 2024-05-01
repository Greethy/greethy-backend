package com.greethy.gateway.core.exception;

import com.greethy.core.domain.exception.BaseException;

import lombok.Getter;

@Getter
public class UserNotFoundException extends BaseException {

    public UserNotFoundException(Integer status, String message) {
        super(status, message);
    }
}
