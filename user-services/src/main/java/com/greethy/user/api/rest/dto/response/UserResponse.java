package com.greethy.user.api.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greethy.user.api.rest.dto.NetworkDto;
import com.greethy.user.api.rest.dto.PersonalDetailDto;
import com.greethy.user.api.rest.dto.PremiumDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Class {@code UserDto} representing a user Data Transfer Object (DTO).
 * This class encapsulates user-related information for RESTful API communication.
 *
 * @author Kien N.Thanh
 */
@Data
@NoArgsConstructor
public class UserResponse {

    @Schema(description = "The name displayed on the user's profile, as entered by the user when creating their account. Important!")
    private String username;

    @Schema(description = "The user's email address, as entered by the user when creating their account. Important!")
    private String email;

    @Schema(description = "Represents whether the user is verified their email by confirmed in their Gmail or not.")
    private Boolean verified;

    private String password;

    private String avatar;

    @JsonProperty("banner_image")
    private String bannerImage;

    private String bio;

    @JsonProperty("personal_info")
    private PersonalDetailDto personalDetail;

    private PremiumDto premium;

    private NetworkDto network;

    private List<String> roles;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("body_specs_ids")
    @Schema(description = "List of body-specs id reference to all body specification that user has created")
    private List<String> bodySpecsIds;
}
