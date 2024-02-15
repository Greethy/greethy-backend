package com.greethy.user.core.domain.exception;

import com.greethy.core.exception.BaseException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserException extends BaseException {

    public UserException(String message) {
        super(message);
    }

}



