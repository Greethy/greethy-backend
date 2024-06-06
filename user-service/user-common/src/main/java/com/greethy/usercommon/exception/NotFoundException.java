package com.greethy.usercommon.exception;

import com.greethy.common.domain.exception.ClientException;

public class NotFoundException extends ClientException {

    public NotFoundException(String message) {
        super(message);
    }
}
