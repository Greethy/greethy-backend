package com.greethy.user.model.exception;

import com.greethy.core.domain.exception.ClientException;

public class DuplicateUniqueFieldException extends ClientException {

    public DuplicateUniqueFieldException() {
        super("Some unique field is duplicated !");
    }
}
