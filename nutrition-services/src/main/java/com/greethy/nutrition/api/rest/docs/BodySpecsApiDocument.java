package com.greethy.nutrition.api.rest.docs;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;

import com.greethy.core.api.response.ErrorResponse;
import com.greethy.nutrition.api.rest.dto.request.CreateBodySpecsRequest;
import com.greethy.nutrition.api.rest.dto.response.BodySpecsCreatedResponse;
import com.greethy.nutrition.api.rest.dto.response.BodySpecsResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@RouterOperations({
    @RouterOperation(
            method = RequestMethod.POST,
            path = "/api/v1/users/{user-id}/body-specs",
            operation =
                    @Operation(
                            parameters = @Parameter(in = ParameterIn.PATH, name = "user-id", required = true),
                            summary = "Create a new Personal Body Specification.",
                            description =
                                    "Create a Body Specification of user by using its age, weight, height, gender, level of activity and goal.",
                            operationId = "createUserBodySpecs",
                            tags = "BodySpecs",
                            requestBody =
                                    @RequestBody(
                                            description = "A Example of Person's Body Specs.",
                                            required = true,
                                            content =
                                                    @Content(
                                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                            schema =
                                                                    @Schema(
                                                                            implementation =
                                                                                    CreateBodySpecsRequest.class,
                                                                            example =
                                                                                    """
														{
															"age" : 22,
															"height" : 1.8,
															"weight" : 72,
															"gender" : 1,
															"activity_type" : "vigorous",
															"goal" : "Gain Muscle"
														}
														""",
                                                                            requiredProperties = {
                                                                                "age",
                                                                                "height",
                                                                                "weight",
                                                                                "gender",
                                                                                "activity_type",
                                                                                "goal"
                                                                            }))),
                            responses = {
                                @ApiResponse(
                                        responseCode = "201",
                                        description = "A User's Body Specs has been created successfully.",
                                        content =
                                                @Content(
                                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                        schema =
                                                                @Schema(
                                                                        implementation = BodySpecsCreatedResponse.class,
                                                                        example =
                                                                                """
																{
																	user_id: string
																}
																"""))),
                                @ApiResponse(
                                        responseCode = "401",
                                        description =
                                                "Bad or expired token. This can happen if the user revoked a token or the access token has expired. You should re-authenticate the user.",
                                        content =
                                                @Content(
                                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                        schema = @Schema(implementation = ErrorResponse.class))),
                                @ApiResponse(
                                        responseCode = "429",
                                        description = "The Greethy Server exceeded its rate limits.",
                                        content =
                                                @Content(
                                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                        schema = @Schema(implementation = ErrorResponse.class)))
                            })),
    @RouterOperation(
            method = RequestMethod.GET,
            path = "/api/v1/body-specs/{body-specs-id}",
            operation =
                    @Operation(
                            parameters = @Parameter(in = ParameterIn.PATH, name = "body-specs-id", required = true),
                            description = "Get Body-Specs by its ID.",
                            operationId = "getBodySpecsById",
                            tags = "BodySpecs",
                            responses = {
                                @ApiResponse(
                                        responseCode = "200",
                                        description = "A Body Specs.",
                                        content =
                                                @Content(
                                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                        schema =
                                                                @Schema(
                                                                        implementation = BodySpecsResponse.class,
                                                                        example =
                                                                                """
																{
																	"age": 22,
																	"height": 1.8,
																	"weight": 72.0,
																	"gender": "male",
																	"bmi": {
																		"index": 22.2,
																		"status": "Normal"
																	},
																	"bmr": {
																		"bmrPerKg": 24.0,
																		"bmrPerDay": 1728.0
																	},
																	"pal": {
																		"value": 2.0,
																		"activityType": "vigorous"
																	},
																	"tdee": 3456.0
																}
																""")))
                            }))
})
public @interface BodySpecsApiDocument {}
