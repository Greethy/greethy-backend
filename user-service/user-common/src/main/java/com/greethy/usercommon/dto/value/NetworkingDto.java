package com.greethy.usercommon.dto.value;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class NetworkingDto {

    @JsonProperty("following_ids")
    private List<String> followingIds;

    @JsonProperty("total_following")
    private Integer totalFollowing;

    @JsonProperty("follower_ids")
    private List<String> followers;

    @JsonProperty("total_followers")
    private Integer totalFollowers;

}
