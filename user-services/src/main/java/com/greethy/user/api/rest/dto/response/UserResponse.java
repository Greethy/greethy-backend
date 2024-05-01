package com.greethy.user.api.rest.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greethy.user.core.domain.entity.Networking;
import com.greethy.user.core.domain.value.PersonalDetail;
import com.greethy.user.core.domain.value.Premium;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Kien N.Thanh
 */
@Data
@NoArgsConstructor
public class UserResponse {

    @Schema(
            description =
                    "The name displayed on the user's profile, as entered by the user when creating their account. Important!")
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
    private PersonalDetail personalDetail;

    private Premium premium;

    private Networking networking;

    private List<String> roles;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("body_specs_ids")
    @Schema(description = "List of body-specs id reference to all body specification that user has created")
    private List<String> bodySpecsIds;
}
