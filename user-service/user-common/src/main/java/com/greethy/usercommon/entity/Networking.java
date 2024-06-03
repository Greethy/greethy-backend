package com.greethy.usercommon.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "networking")
public class Networking {

    @MongoId
    private String id;

    @Field(name = "following_ids")
    private List<String> followingIds;

    @Field(name = "total_following")
    private Integer totalFollowing;

    @Field(name = "follower_ids")
    private List<String> followersIds;

    @Field(name = "total_followers")
    private Integer totalFollowers;

    public Networking(String id) {
        this.id = id;
        this.followingIds = new ArrayList<>();
        this.totalFollowing = 0;
        this.followersIds = new ArrayList<>();
        this.totalFollowers = 0;
    }
}
