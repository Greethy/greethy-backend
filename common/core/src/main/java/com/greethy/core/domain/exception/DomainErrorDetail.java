package com.greethy.core.domain.exception;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class DomainErrorDetail {

    private String name;

    private Integer status;

    private String message;

}
