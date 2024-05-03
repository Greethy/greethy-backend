package com.greethy.nutrition.api.rest.controller;

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
import com.greethy.nutrition.api.rest.dto.request.CreateFoodRequest;
import com.greethy.nutrition.api.rest.dto.response.BodySpecsCreatedResponse;
import com.greethy.nutrition.api.rest.dto.response.BodySpecsResponse;
import com.greethy.nutrition.api.rest.dto.response.FoodCreatedResponse;

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
                            operationId = "createUserBodySpecs",
                            parameters = @Parameter(in = ParameterIn.PATH, name = "user-id", required = true),
                            summary = "Create a new Personal Body Specification.",
                            description =
                                    "Create a Body Specification of user by using its age, weight, height, gender, level of activity and goal.",
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
            path = "/api/v1/users/{user-id}/body-specs",
            operation =
                    @Operation(
                            operationId = "getAllUserBodySpecs",
                            parameters = @Parameter(in = ParameterIn.PATH, name = "user-id", required = true),
                            summary = "Get all Personal Body Specification.",
                            description = "Get all Body Specification of user",
                            tags = "BodySpecs",
                            responses = {
                                @ApiResponse(
                                        responseCode = "200",
                                        description = "All User's Body Specs.",
                                        content =
                                                @Content(
                                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                        schema =
                                                                @Schema(
                                                                        example =
                                                                                """
														[
															{
																"age": 22,
																"height": 1.8,
																"weight": 72,
																"gender": "male",
																"bmi": {
																"index": 22.2,
																"status": "Normal"
																},
																"bmr": {
																	"bmrPerKg": 24,
																	"bmrPerDay": 1728
																},
																"pal": {
																	"value": 2,
																	"activityType": "vigorous"
																},
																"tdee": 3456
															}
														]
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
            method = RequestMethod.POST,
            path = "/api/v1/users/{user-id}/food",
            operation =
                    @Operation(
                            operationId = "createFood",
                            parameters = @Parameter(in = ParameterIn.PATH, name = "user-id", required = true),
                            description = "User create new their own food",
                            tags = "Foods",
                            requestBody =
                                    @RequestBody(
                                            description = "A Example of Food.",
                                            required = true,
                                            content =
                                                    @Content(
                                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                            schema =
                                                                    @Schema(
                                                                            implementation = CreateFoodRequest.class,
                                                                            example =
                                                                                    """
																		{
																			"name" : "Bò Hầm rau củ",
																			"recipe" : "Bước 1: chuẩn bị nguyên liệu\\\\n Bước 2: Bắc bếp\\\\n Bước 3: Thưởng thức món ăn",
																			"tips" : "Để lửa nhỏ 150 độ C trong khoảng thời gian 1 tiếng để thịt bò được nhừ",
																			"description" : "Bò là một trong những thực phẩm giúp ta bổ sung nhiều chất sắt. Thịt bò rất tốt đối với những người thiếu máu, đặc biệt là các chị em khi đến ngày dâu hay những người đang trong quá trình giảm cân. Tuần này, Greethy giới thiệu với bạn món tủ của Greethy - bò hầm rau củ",
																			"image_url" : "https://www.facebook.com/photo?fbid=122147869472086639&set=pcb.122147870048086639",
																			"video-url" : "https://www.youtube.com/watch?v=m2c8L6rDLWg&list=RDGMEMQ1dJ7wXfLlqCjwV0xfSNbA&index=5",
																			"open" : true
																		}
																""",
                                                                            requiredProperties = {
                                                                                "name",
                                                                                "recipe",
                                                                                "tips",
                                                                                "description",
                                                                                "image_url",
                                                                                "video-url",
                                                                                "open"
                                                                            }))),
                            responses = {
                                @ApiResponse(
                                        responseCode = "201",
                                        description = "Created new Food with empty Ingredients.",
                                        content =
                                                @Content(
                                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                        schema =
                                                                @Schema(
                                                                        implementation = FoodCreatedResponse.class,
                                                                        example =
                                                                                """
																		{
																			"food_id": "7950450f-6186-43c0-9695-99a2fb8b8a02"
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
            method = RequestMethod.DELETE,
            path = "/api/v1/users/{user-id}/body-specs/{body-specs-id}",
            operation =
                    @Operation(
                            operationId = "deleteUserBodySpecs",
                            parameters = {
                                @Parameter(in = ParameterIn.PATH, name = "user-id", required = true),
                                @Parameter(in = ParameterIn.PATH, name = "body-specs-id", required = true)
                            },
                            description = "Users delete their BodySpecs",
                            tags = "BodySpecs",
                            responses = {
                                @ApiResponse(
                                        responseCode = "200",
                                        description = "User's BodySpecs was deleted successfully"),
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
                            operationId = "getBodySpecsById",
                            parameters = @Parameter(in = ParameterIn.PATH, name = "body-specs-id", required = true),
                            description = "Get Body-Specs by its ID.",
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
				method = RequestMethod.PUT,
				path = "/api/v1/body-specs/{body-specs-id}",
				operation =
				@Operation(
						operationId = "updateBodySpecsById",
						parameters = @Parameter(in = ParameterIn.PATH, name = "body-specs-id", required = true),
						description = "Update Body-Specs by its ID.",
						summary = "Update Body-Specs by its ID.",
						tags = "BodySpecs",
						responses = {
								@ApiResponse(
										responseCode = "200",
										description = "A Updated Body Specs.",
										content =
										@Content(
												mediaType = MediaType.APPLICATION_JSON_VALUE,
												schema =
												@Schema(
														implementation = BodySpecsResponse.class,
														example =
																"""
                                                        {
                                                            "age": 20,
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
						}))
})
public @interface NutritionOpenApi {}
