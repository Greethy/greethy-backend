package com.greethy.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * Represents an error response with details such as date, code, status, and message.
 *
 * @author ThanhKien
 */
@Setter
@Builder
@AllArgsConstructor
public class ErrorResponse {

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss.SSS")
    private final LocalDateTime date = ZonedDateTime.now().toLocalDateTime();

    private final int code;

    private final String status;

    private final String message;

}
