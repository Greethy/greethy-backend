package com.greethy.usercommon.exception;

import com.greethy.common.domain.exception.ClientException;

public class WrongPasswordException extends ClientException {
    public WrongPasswordException(String message) {
        super(message);
    }
}
