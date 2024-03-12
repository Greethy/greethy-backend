package com.greethy.core.domain.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DomainErrorDetail {

    private String name;

    private Integer status;

    private String message;

}
