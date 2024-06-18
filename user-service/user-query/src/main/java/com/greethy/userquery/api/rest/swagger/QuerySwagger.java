package com.greethy.userquery.api.rest.swagger;

import com.greethy.common.api.response.ErrorResponse;
import com.greethy.usercommon.dto.response.UserProfileResponse;
import com.greethy.usercommon.dto.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
                method = RequestMethod.GET,
                path = "/api/v1/users",
                produces = MediaType.APPLICATION_JSON_VALUE,
                operation = @Operation(
                        tags = "User Query",
                        operationId = "getAllUserByPagination",
                        summary = "Get all users with pagination",
                        description = "Retrieve a list of users with pagination support. You can specify page and size parameters to control the number of results.",
                        parameters = {
                                @Parameter(
                                        name = "offset",
                                        in = ParameterIn.QUERY,
                                        description = "The page number to retrieve, default value: 0, range 0 - ??",
                                        schema = @Schema(type = "integer", defaultValue = "0", minimum = "0")),
                                @Parameter(
                                        name = "limit",
                                        in = ParameterIn.QUERY,
                                        description = "The number of records per page, default value: 10, range 1 - 20",
                                        schema = @Schema(type = "integer", defaultValue = "10", minimum = "1", maximum = "20")),
                                @Parameter(
                                        name = "sort",
                                        in = ParameterIn.QUERY,
                                        description = "Sort by key, '+' is ASC, '-' is DESC (required), default value: +id",
                                        schema = @Schema(type = "String", defaultValue = "+id"))
                        },
                        responses = {
                                @ApiResponse(
                                        responseCode = "200",
                                        description = "Successful retrieval of user list",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                array = @ArraySchema(schema = @Schema(implementation = UserResponse.class)))),
                                @ApiResponse(
                                        responseCode = "401",
                                        description = "User authorization has been refused for those credentials.",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = ErrorResponse.class))),
                                @ApiResponse(
                                        responseCode = "404",
                                        description = "he requested resource could not be found. This error can be due to a temporary or permanent condition.",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = ErrorResponse.class))),
                                @ApiResponse(
                                        responseCode = "429",
                                        description = "The app has exceeded its rate limits.",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = ErrorResponse.class)))
                        })),
        @RouterOperation(
                method = RequestMethod.GET,
                path = "/api/v1/me",
                produces = MediaType.APPLICATION_JSON_VALUE,
                operation = @Operation(
                        tags = "User Query",
                        operationId = "getCurrentUserProfile",
                        summary = "Get current User",
                        description = "des",
                        responses = {
                                @ApiResponse(
                                        responseCode = "200",
                                        description = "Successful retrieval of user list",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                array = @ArraySchema(schema = @Schema(implementation = UserProfileResponse.class)))),
                                @ApiResponse(
                                        responseCode = "401",
                                        description = "User authorization has been refused for those credentials.",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = ErrorResponse.class))),
                                @ApiResponse(
                                        responseCode = "429",
                                        description = "The app has exceeded its rate limits.",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = ErrorResponse.class)))
                        }
                )
        )
})
public @interface QuerySwagger {
}
