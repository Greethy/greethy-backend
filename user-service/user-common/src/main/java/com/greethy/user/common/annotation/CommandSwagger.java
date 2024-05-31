package com.greethy.user.common.annotation;

import com.greethy.core.api.response.ErrorResponse;
import com.greethy.user.common.dto.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;

import com.greethy.user.common.dto.request.command.RegisterUserCommand;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@RouterOperations({
        @RouterOperation(
                method = RequestMethod.POST,
                path = "/api/v1/users",
                operation = @Operation(
                        summary = "Register a Greethy User using Username/Email/Password",
                        description = "Register a new User using basic authenticating Username/Password with an Email to verify",
                        operationId = "registerUser",
                        tags = "Users",
                        requestBody = @RequestBody(
                                description = "User basic authenticate information",
                                required = true,
                                content = @Content(
                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                        schema = @Schema(implementation = RegisterUserCommand.class)
                                )
                        ),
                        responses = {
                                @ApiResponse(
                                        responseCode = "201",
                                        description = "A Greethy user has been registered successfully.",
                                        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                                ),
                                @ApiResponse(
                                        responseCode = "400",
                                        description = "The request could not be understood by the server due to malformed syntax.",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = UserResponse.class))
                                ),
                                @ApiResponse(
                                        responseCode = "401",
                                        description = "User authorization has been refused for those credentials.",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = ErrorResponse.class))
                                ),
                                @ApiResponse(
                                        responseCode = "429",
                                        description = "The app has exceeded its rate limits.",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = ErrorResponse.class))
                                )
                }
        )
)
})

public @interface CommandSwagger {
}
