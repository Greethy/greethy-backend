package com.greethy.user.core.domain.exception;

public class DuplicateUniqueFieldException extends UserException {

    public DuplicateUniqueFieldException() {
        super("Username or email already used !");
    }

}
