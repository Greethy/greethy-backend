package com.greethy.usercommon.exception;

import com.greethy.common.domain.exception.ClientException;

public class InvalidUsernameOrEmailException extends ClientException {

    public InvalidUsernameOrEmailException(String message) {
        super(message);
    }
}
