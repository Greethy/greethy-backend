package com.greethy.gateway.core.exception;

import com.greethy.core.domain.exception.BaseException;
import lombok.Getter;

@Getter
public class AccountExistedException extends BaseException {

    public AccountExistedException(Integer status, String message) {
        super(status, message);
    }
}
