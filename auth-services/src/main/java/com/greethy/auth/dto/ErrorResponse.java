package com.greethy.auth.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.Date;

/**
 * Represents an error response with details such as date, code, status, and message.
 *
 * @author ThanhKien
 */
@Getter
public class ErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private final Date date;

    private int code;

    private String status;

    private String message;

    /**
     * Default constructor that sets the date to the current timestamp.
     */
    public ErrorResponse() {
        date = Date.from(Instant.now());
    }

    /**
     * Constructor to create an ErrorResponse with specified HTTP status and message.
     *
     * @param status  The HTTP status of the error.
     * @param message A detailed message describing the error.
     */
    public ErrorResponse(HttpStatus status, String message){
        this();
        this.code = status.value();
        this.status = status.name();
        this.message = message;
    }
}
