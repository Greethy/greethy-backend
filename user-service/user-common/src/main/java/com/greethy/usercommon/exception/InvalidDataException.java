package com.greethy.usercommon.exception;

import com.greethy.common.domain.exception.ClientException;

public class InvalidDataException extends ClientException {

    public InvalidDataException(String message) {
        super(message);
    }

}
