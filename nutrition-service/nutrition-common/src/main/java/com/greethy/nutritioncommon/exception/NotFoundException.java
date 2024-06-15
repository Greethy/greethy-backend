package com.greethy.nutritioncommon.exception;

import com.greethy.common.domain.exception.ClientException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends ClientException {

    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND.value(), message);
    }

}
