package com.greethy.user.api.rest.docs;

import com.greethy.user.api.rest.dto.request.RegisterUserRequest;
import io.swagger.v3.oas.annotations.Operation;
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
                path = "/api/v1/user",
                operation = @Operation(
                        description = "Register new user",
                        operationId = "registerUser",
                        tags = "user",
                        requestBody = @RequestBody(
                                description = "User basic authenticate information",
                                required = true,
                                content = @Content(schema = @Schema(
                                        implementation = RegisterUserRequest.class,
                                        requiredProperties = {"username", "email", "password"}
                                ))
                        ),
                        responses = {
                                @ApiResponse(
                                        responseCode = "200",
                                        description = "User registered successfully !",
                                        content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
                        }
                )
        )
})
public @interface UserApiDocs {}
