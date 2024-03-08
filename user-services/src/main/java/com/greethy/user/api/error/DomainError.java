package com.greethy.user.api.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class DomainError {

    private String name;

    private HttpStatus status;

    private String message;

}
