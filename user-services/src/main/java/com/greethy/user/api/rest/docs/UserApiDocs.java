package com.greethy.user.api.rest.docs;

import com.greethy.user.api.rest.dto.UserDto;
import com.greethy.user.api.rest.dto.request.RegisterUserRequest;
import com.greethy.user.api.rest.dto.response.ErrorResponse;
import com.greethy.user.api.rest.dto.response.UsersLookupResponse;
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
                path = "/api/v1/user",
                operation = @Operation(
                        summary = "Register User using Username/Email/Password",
                        description = "Register a new Greethy User using basic authenticating Username/Password with an Email to verify",
                        operationId = "registerUser",
                        tags = "Users",
                        requestBody = @RequestBody(
                                description = "User basic authenticate information",
                                required = true,
                                content = @Content(
                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                        schema = @Schema(
                                                implementation = RegisterUserRequest.class,
                                                example = """
                                                        {
                                                            "username" : "kienThanh00",
                                                            "email" : "kien.nt112002@gmail.com",
                                                            "password" : "kien123"
                                                        }
                                                        """,
                                                requiredProperties = {"username", "email", "password"})
                                )
                        ),
                        responses = {
                                @ApiResponse(
                                        responseCode = "201",
                                        description = "A Greethy user has been registered successfully.",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE
                                        )
                                ),
                                @ApiResponse(
                                        responseCode = "400",
                                        description = "username or email already in used.",
                                        content = @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = ErrorResponse.class)
                                        )
                                ),
                                @ApiResponse(
                                        responseCode = "401",
                                        description = "Bad or expired token. This can happen if the user revoked a token or the access token has expired. You should re-authenticate the user.",
                                        content = @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = ErrorResponse.class)
                                        )
                                ),
                                @ApiResponse(
                                        responseCode = "429",
                                        description = "The Greethy Server exceeded its rate limits.",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = ErrorResponse.class)
                                        )
                                )
                        }
                )
        ),
        @RouterOperation(
                method = RequestMethod.PUT,
                path = "/api/v1/user/{user_id}",
                operation = @Operation(
                        parameters = @Parameter(name = "user_id", required = true),
                        description = "Update user's public profile (Exclude username, password and email)",
                        operationId = "updateUserById",
                        tags = "Users",
                        responses = {
                                @ApiResponse(
                                        responseCode = "200",
                                        description = "A updated profile detail of user.",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE
                                        )
                                ),
                                @ApiResponse(
                                        responseCode = "401",
                                        description = "Bad or expired token. This can happen if the user revoked a token or the access token has expired. You should re-authenticate the user.",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = ErrorResponse.class)
                                        )
                                ),
                                @ApiResponse(
                                        responseCode = "403",
                                        description = "Bad OAuth request (wrong consumer key, bad nonce, expired timestamp...). Unfortunately, re-authenticating the user won't help here.",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = ErrorResponse.class)
                                        )
                                ),
                                @ApiResponse(
                                        responseCode = "429",
                                        description = "The Greethy Server exceeded its rate limits.",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = ErrorResponse.class)
                                        )
                                )
                        }
                )
        ),
        @RouterOperation(
                method = RequestMethod.GET,
                path = "/api/v1/user/{user_id}",
                operation = @Operation(
                        parameters = @Parameter(in = ParameterIn.PATH, name = "user_id", description = "The user's ID"),
                        summary = "Get User's Public Profile By Id",
                        description = "Get public profile information about a Greethy user.",
                        operationId = "GetUserById",
                        tags = "Users",
                        responses = {
                                @ApiResponse(
                                        responseCode = "200",
                                        description = "A updated profile detail of user.",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = UserDto.class)
                                        )
                                ),
                                @ApiResponse(
                                        responseCode = "401",
                                        description = "Bad or expired token. This can happen if the user revoked a token or the access token has expired. You should re-authenticate the user.",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = ErrorResponse.class)
                                        )
                                ),
                                @ApiResponse(
                                        responseCode = "403",
                                        description = "Bad OAuth request (wrong consumer key, bad nonce, expired timestamp...). Unfortunately, re-authenticating the user won't help here.",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = ErrorResponse.class)
                                        )
                                ),
                                @ApiResponse(
                                        responseCode = "404",
                                        description = "The user's id from Path variable cannot found in Greethy Server's databases.",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = ErrorResponse.class)
                                        )
                                ),
                                @ApiResponse(
                                        responseCode = "429",
                                        description = "The Greethy Server exceeded its rate limits.",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = ErrorResponse.class)
                                        )
                                )
                        }
                )
        ),
        @RouterOperation(
                method = RequestMethod.GET,
                path = "/api/v1/user/",
                operation = @Operation(
                        summary = "Get All User's Profile From Databases (FOR TEST ONLY)",
                        description = "Get all public profile information from Greethy users.",
                        operationId = "GetAllUser",
                        tags = "Users",
                        responses = {
                                @ApiResponse(
                                        responseCode = "200",
                                        description = "The list of all user's profile in databases.",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = UsersLookupResponse.class)
                                        )
                                ),
                                @ApiResponse(
                                        responseCode = "401",
                                        description = "Bad or expired token. This can happen if the user revoked a token or the access token has expired. You should re-authenticate the user.",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = ErrorResponse.class)
                                        )
                                ),
                                @ApiResponse(
                                        responseCode = "403",
                                        description = "Bad OAuth request (wrong consumer key, bad nonce, expired timestamp...). Unfortunately, re-authenticating the user won't help here.",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = ErrorResponse.class)
                                        )
                                ),
                                @ApiResponse(
                                        responseCode = "429",
                                        description = "The Greethy Server exceeded its rate limits.",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = ErrorResponse.class)
                                        )
                                )
                        }
                )
        ),
        @RouterOperation(
                method = RequestMethod.DELETE,
                path = "/api/v1/user/{user_id}",
                operation = @Operation(
                        parameters = @Parameter(in = ParameterIn.PATH, name = "user_id", description = "The user's ID"),
                        summary = "Delete User by id",
                        description = "delete the Greethy user by id permanently (TEST ONLY)",
                        operationId = "DeleteUserById",
                        tags = "Users",
                        responses = {
                                @ApiResponse(
                                        responseCode = "204",
                                        description = "Return nothing"
                                ),
                                @ApiResponse(
                                        responseCode = "401",
                                        description = "Bad or expired token. This can happen if the user revoked a token or the access token has expired. You should re-authenticate the user.",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = ErrorResponse.class)
                                        )
                                ),
                                @ApiResponse(
                                        responseCode = "403",
                                        description = "Bad OAuth request (wrong consumer key, bad nonce, expired timestamp...). Unfortunately, re-authenticating the user won't help here.",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = ErrorResponse.class)
                                        )
                                ),
                                @ApiResponse(
                                        responseCode = "429",
                                        description = "The Greethy Server exceeded its rate limits.",
                                        content = @Content(
                                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                schema = @Schema(implementation = ErrorResponse.class)
                                        )
                                )
                        }
                )
        )
})
public @interface UserApiDocs {
}
