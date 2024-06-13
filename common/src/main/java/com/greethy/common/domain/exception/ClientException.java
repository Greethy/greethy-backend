package com.greethy.common.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ClientException extends BaseException {

    public ClientException(String message) {
        super(HttpStatus.BAD_REQUEST.value(), message);
    }

    public ClientException(int status, String message) {
        super(status, message);
    }
}
