package com.greethy.usercommon.exception;

import com.greethy.core.domain.exception.ClientException;

public class WrongPasswordException extends ClientException {
    public WrongPasswordException(String message) {
        super(message);
    }
}
