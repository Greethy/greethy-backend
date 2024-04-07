package com.greethy.user.core.domain.exception;

import com.greethy.core.domain.exception.BaseException;
import org.springframework.http.HttpStatus;

public class DuplicateUniqueFieldException extends BaseException {

    public DuplicateUniqueFieldException() {
        super(HttpStatus.BAD_REQUEST.value(), "Some unique field is duplicated !");
    }

}
