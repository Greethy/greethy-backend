package com.greethy.common.infra.component.validation;

import com.greethy.common.domain.exception.ClientException;
import com.greethy.common.infra.component.i18n.Translator;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RequestValidation {

    private final Translator translator;

    private final Validator validator;

    public void validate(Object object) {
        var constraintViolations = validator.validate(object);
        if(!constraintViolations.isEmpty()) {
            var errorMessage = constraintViolations.stream()
                    .map(violation -> {
                        var message = violation.getMessage();
                        return translator.getLocalizedMessage(message);
                    })
                    .collect(Collectors.joining(", "));
            throw new ClientException(errorMessage);
        }
    }

}
