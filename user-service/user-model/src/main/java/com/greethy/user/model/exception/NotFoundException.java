package com.greethy.user.model.exception;

import com.greethy.core.domain.exception.BaseException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {

    public NotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), "This resources cannot found !");
    }
}
