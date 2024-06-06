package com.greethy.common.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    @Schema(description = "The HTTP status code, Range 400-599.", examples = {"400", "401", "403", "429", "500"})
    private Integer status;

    @Schema(description = "A short description of the cause of the error.", examples = {"400", "401", "403", "429", "500"})
    private String message;
}
