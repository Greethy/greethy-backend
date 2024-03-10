package com.greethy.user.api.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class NetworkDto {

    private List<String> following;

    @JsonProperty("total_following")
    private Integer totalFollowing;

    private List<String> followers;

    @JsonProperty("total_followers")
    private Integer totalFollowers;

}
