package com.greethy.user.core.domain.exception;

import com.greethy.core.domain.exception.BaseException;
import lombok.Getter;

@Getter
public class UserException extends BaseException {

    public UserException(String message) {
        super(message);
    }

}



