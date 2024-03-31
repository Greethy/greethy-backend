package com.greethy.core.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse{

    @Schema(description = "The HTTP status code, Range 400-599.")
    private Integer status;

    @Schema(description = "A short description of the cause of the error.")
    private String message;
}