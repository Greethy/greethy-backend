package com.greethy.user.model.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
@Document(collection = "networking")
public class Networking {

    @MongoId
    private String id;

    private List<String> following;

    @Field(name = "total_following")
    private Integer totalFollowing = 0;

    private List<String> followers;

    @Field(name = "total_followers")
    private Integer totalFollowers = 0;

}
