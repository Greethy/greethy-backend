package com.greethy.personal.domain.exception;

import com.greethy.core.exception.BaseException;
import lombok.Getter;

@Getter
public class DuplicateUniqueFieldException extends BaseException {

    public DuplicateUniqueFieldException(String message) {
        super(message);
    }

}
