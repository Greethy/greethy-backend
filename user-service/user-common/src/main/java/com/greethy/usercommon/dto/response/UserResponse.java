package com.greethy.usercommon.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greethy.usercommon.dto.value.NetworkingDto;
import com.greethy.usercommon.dto.value.PremiumDto;
import com.greethy.usercommon.dto.value.RoleDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserResponse {

    @Schema(description = "Unique identifier of the user")
    private String id;

    @Schema(description = "Username of the user")
    private String username;

    @Schema(description = "Email address of the user")
    private String email;

    @Schema(description = "Password of the user, encrypted", accessMode = Schema.AccessMode.WRITE_ONLY)
    private String password;

    @Schema(description = "Verification status of the user's email")
    private Boolean verified;

    @Schema(description = "URL to the user's avatar image")
    private String avatar;

    @Schema(description = "URL to the user's banner image")
    @JsonProperty("banner_url")
    private String bannerUrl;

    @Schema(description = "Biography of the user")
    private String bio;

    @Schema(description = "Premium membership details of the user")
    private PremiumDto premium;

    @Schema(description = "Networking information of the user")
    private NetworkingDto networking;

    @Schema(description = "Roles assigned to the user")
    private List<RoleDto> roles;

    @Schema(description = "List of body specifications IDs associated with the user")
    @JsonProperty("body_specs_ids")
    private List<String> bodySpecsIds;

    @Schema(description = "Timestamp when the user was created", format = "date-time")
    @JsonProperty("created_at")
    private Date createdAt;

    @Schema(description = "Timestamp when the user's information was last updated", format = "date-time")
    @JsonProperty("updated_at")
    private Date updatedAt;

}
