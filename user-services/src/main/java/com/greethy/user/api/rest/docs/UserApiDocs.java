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
                        description = "Register a new Greethy User using basic authenticating Username/Password with an Email to verify",
                        operationId = "registerUser",
                        tags = "Users",
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
                                        responseCode = "201",
                                        description = "A Greethy user has been registered successfully.",
                                        content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
                                @ApiResponse(
                                        responseCode = "400",
                                        description = "username or email already in used."
                                ),
                                @ApiResponse(
                                        responseCode = "401",
                                        description = "Bad or expired token. This can happen if the user revoked a token or the access token has expired. You should re-authenticate the user."
                                ),
                                @ApiResponse(
                                        responseCode = "429",
                                        description = "The Greethy Server exceeded its rate limits."
                                )
                        }
                )
        ),
        @RouterOperation(
                method = RequestMethod.PUT,
                path = "/api/v1/user/{user_id}",
                operation = @Operation(
                        description = "Update user's public profile (Exclude username, password and email)",
                        operationId = "updateUserById",
                        tags = "Users",
                        responses = {
                                @ApiResponse(
                                        responseCode = "200",
                                        description = "A updated profile detail of user."
                                ),
                                @ApiResponse(
                                        responseCode = "401",
                                        description = "Bad or expired token. This can happen if the user revoked a token or the access token has expired. You should re-authenticate the user."
                                ),
                                @ApiResponse(
                                        responseCode = "403",
                                        description = "Bad OAuth request (wrong consumer key, bad nonce, expired timestamp...). Unfortunately, re-authenticating the user won't help here."
                                ),
                                @ApiResponse(
                                        responseCode = "429",
                                        description = "The Greethy Server exceeded its rate limits."
                                )
                        }
                )
        ),
        @RouterOperation(
                method = RequestMethod.GET,
                path = "/api/v1/user/{user_id}",
                operation = @Operation(
                        description = "Get public profile information about a Greethy user.",
                        operationId = "GetUserById",
                        tags = "Users",
                        responses = {
                                @ApiResponse()
                        }
                )
        )
})
public @interface UserApiDocs {
}
