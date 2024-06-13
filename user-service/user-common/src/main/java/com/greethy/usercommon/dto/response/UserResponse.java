package com.greethy.usercommon.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.greethy.usercommon.dto.value.NetworkingDto;
import com.greethy.usercommon.dto.value.PersonalDetailDto;
import com.greethy.usercommon.dto.value.PremiumDto;
import com.greethy.usercommon.dto.value.RoleDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
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

    @JsonProperty("avatar_url")
    @Schema(description = "URL to the user's avatar image")
    private String avatarUrl;

    @JsonProperty("banner_url")
    @Schema(description = "URL to the user's banner image")
    private String bannerUrl;

    @Schema(description = "Biography of the user")
    private String bio;

    @Schema(description = "Premium membership details of the user")
    private PremiumDto premium;

    @JsonProperty("personal_detail")
    private PersonalDetailDto personalDetail;

    @Schema(description = "Networking information of the user")
    private NetworkingDto networking;

    private List<String> labels;

    @Schema(description = "Roles assigned to the user")
    private List<RoleDto> roles;

    @Schema(description = "Timestamp when the user was created", format = "date-time")
    @JsonProperty("created_at")
    private Date createdAt;

    @Schema(description = "Timestamp when the user's information was last updated", format = "date-time")
    @JsonProperty("updated_at")
    private Date updatedAt;

}
