package com.greethy.user.core.domain.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
public class Networking {

    private List<String> following;

    @Field(name = "total_following")
    private Integer totalFollowing = 0;

    private List<String> followers;

    @Field(name = "total_followers")
    private Integer totalFollowers = 0;


}
