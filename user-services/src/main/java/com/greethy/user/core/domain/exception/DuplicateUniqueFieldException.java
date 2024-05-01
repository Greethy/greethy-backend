package com.greethy.user.core.domain.exception;

import org.springframework.http.HttpStatus;

import com.greethy.core.domain.exception.BaseException;

public class DuplicateUniqueFieldException extends BaseException {

    public DuplicateUniqueFieldException() {
        super(HttpStatus.BAD_REQUEST.value(), "Some unique field is duplicated !");
    }
}
