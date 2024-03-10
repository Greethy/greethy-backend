package com.greethy.user.core.domain.exception;

import com.greethy.core.domain.exception.BaseException;

public class DuplicateUniqueFieldException extends BaseException {

    public DuplicateUniqueFieldException(Integer status, String message) {
        super(status, message);
    }

}
