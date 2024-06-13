package com.greethy.usercommon.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "networking")
public class Networking {

    @MongoId(FieldType.STRING)
    private String id;

    @Field(name = "following_ids")
    private List<String> followingIds;

    @Field(name = "total_following")
    private Integer totalFollowing;

    @Field(name = "follower_ids")
    private List<String> followerIds;

    @Field(name = "total_followers")
    private Integer totalFollowers;

    public Networking(String id) {
        this.id = id;
        this.followingIds = new ArrayList<>();
        this.totalFollowing = 0;
        this.followerIds = new ArrayList<>();
        this.totalFollowers = 0;
    }
}
