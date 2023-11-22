package com.greethy.auth.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * Represents an error response with details such as date, code, status, and message.
 *
 * @author ThanhKien
 */
@Getter
public class ErrorResponse {

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss.SSS")
    private final LocalDateTime date;

    private int code;

    private String status;

    private String message;

    /**
     * Default constructor that sets the date to the current timestamp.
     */
    public ErrorResponse() {
        date = ZonedDateTime.now().toLocalDateTime();
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
