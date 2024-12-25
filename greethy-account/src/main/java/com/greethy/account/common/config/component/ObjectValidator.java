package com.greethy.account.common.config.component;

import com.greethy.account.common.exception.InvalidInputException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ObjectValidator {

    private static final String DELIMITER_COMMA = ", ";
    private final Validator validator;

    public <T> void validate(final T object) throws InvalidInputException {
        var errors = validator.validate(object);
        if (!errors.isEmpty()) {
            String errorDetails = errors.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(DELIMITER_COMMA));
            throw new InvalidInputException(errorDetails, HttpStatus.BAD_REQUEST);
        }
    }

}
