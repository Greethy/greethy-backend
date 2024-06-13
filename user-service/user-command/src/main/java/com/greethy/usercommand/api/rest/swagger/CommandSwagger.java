package com.greethy.usercommand.api.rest.swagger;

import com.greethy.common.api.response.ErrorResponse;
import com.greethy.usercommon.dto.request.command.UpdateUserProfileCommand;
import com.greethy.usercommon.dto.request.command.UserLoginCommand;
import com.greethy.usercommon.dto.response.AuthResponse;
import com.greethy.usercommon.dto.response.UserResponse;
import com.greethy.usercommon.dto.request.command.UserRegistrationCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@RouterOperations({
        @RouterOperation(
                method = RequestMethod.POST,
                path = "/auth/login",
                operation = @Operation(
                        operationId = "greethyLogin",
                        summary = "User login",
                        description = "Provide Access-token for Authentication",
                        tags = "Auth",
                        requestBody = @RequestBody(
                                description = "User basic authentication information",
                                required = true,
                                content = @Content(
                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                        schema = @Schema(implementation = UserLoginCommand.class)
                                )
                        ),
                        responses = {
                                @ApiResponse(
                                        responseCode = "201",
                                        description = "A Greethy user has been registered successfully.",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = AuthResponse.class))
                                ),
                                @ApiResponse(
                                        responseCode = "400",
                                        description = "The request could not be understood by the server due to malformed syntax.",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = ErrorResponse.class))
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
                                                schema = @Schema(implementation = ErrorResponse.class)))
                        }
                )
        ),
        @RouterOperation(
                method = RequestMethod.POST,
                path = "/auth/register",
                operation = @Operation(
                        operationId = "greethyRegister",
                        summary = "User registration",
                        description = "Register a new user account",
                        tags = "Auth",
                        requestBody = @RequestBody(
                                description = "User registration information",
                                required = true,
                                content = @Content(
                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                        schema = @Schema(implementation = UserRegistrationCommand.class)
                                )
                        ),
                        responses = {
                                @ApiResponse(
                                        responseCode = "201",
                                        description = "User has been registered successfully.",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = AuthResponse.class))
                                ),
                                @ApiResponse(
                                        responseCode = "400",
                                        description = "The request could not be understood by the server due to malformed syntax.",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = ErrorResponse.class))
                                ),
                                @ApiResponse(
                                        responseCode = "409",
                                        description = "Conflict - User with provided username/email already exists.",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = ErrorResponse.class))
                                ),
                                @ApiResponse(
                                        responseCode = "429",
                                        description = "The app has exceeded its rate limits.",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = ErrorResponse.class)))
                        }
                )
        ),
        @RouterOperation(
                method = RequestMethod.POST,
                path = "/api/v1/users",
                operation = @Operation(
                        operationId = "registerUser",
                        summary = "Register a Greethy User using Username/Email/Password",
                        description = "Register a new User using basic authenticating Username/Password with an Email to verify",
                        tags = "Users",
                        requestBody = @RequestBody(
                                description = "User basic authenticate information",
                                required = true,
                                content = @Content(
                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                        schema = @Schema(implementation = UserRegistrationCommand.class)
                                )
                        ),
                        responses = {
                                @ApiResponse(
                                        responseCode = "201",
                                        description = "A Greethy user has been registered successfully.",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = UserResponse.class))
                                ),
                                @ApiResponse(
                                        responseCode = "400",
                                        description = "The request could not be understood by the server due to malformed syntax.",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = ErrorResponse.class))
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
                                                schema = @Schema(implementation = ErrorResponse.class)))
                        })
        ),
        @RouterOperation(
                method = RequestMethod.PUT,
                path = "/api/v1/users/{user-id}",
                operation = @Operation(
                        operationId = "updateUserProfile",
                        summary = "Update a Greethy User's Profile",
                        description = "Update a User's Profile ",
                        tags = "Users",
                        parameters = @Parameter(name = "user-id", required = true, in = ParameterIn.PATH),
                        requestBody = @RequestBody(
                                description = "User profile",
                                required = true,
                                content = @Content(
                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                        schema = @Schema(implementation = UpdateUserProfileCommand.class)
                                )
                        )
                )
        )
})

public @interface CommandSwagger {
}
