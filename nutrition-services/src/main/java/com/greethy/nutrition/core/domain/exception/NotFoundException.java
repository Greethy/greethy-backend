package com.greethy.nutrition.core.domain.exception;

import org.springframework.http.HttpStatus;

import com.greethy.core.domain.exception.BaseException;

public class NotFoundException extends BaseException {

    public NotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), "This resources cannot found !");
    }
}
