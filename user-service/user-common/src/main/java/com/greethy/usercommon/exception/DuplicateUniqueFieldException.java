package com.greethy.usercommon.exception;

import com.greethy.core.domain.exception.ClientException;

public class DuplicateUniqueFieldException extends ClientException {

    public DuplicateUniqueFieldException(String message) {
        super(message);
    }
}
