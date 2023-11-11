package com.greethy.auth.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.Date;

@Getter
public class ErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private final Date date;

    private int code;

    private String status;

    private String message;

    public ErrorResponse() {
        date = Date.from(Instant.now());
    }

    public ErrorResponse(HttpStatus status, String message){
        this();
        this.code = status.value();
        this.status = status.name();
        this.message = message;
    }
}
