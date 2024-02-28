package com.greethy.user.core.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This class represent for user, including login information,
 * personal information, body specified, and referenced ids to other documents.
 *
 * @author ThanhKien
 * */
@Data
@Document(collection = "users")
public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    private String username;

    @Indexed(unique = true)
    private String email;

    private Boolean verified;

    private String password;

    private String avatar;

    @Field(name = "banner_image")
    private String bannerImage;

    private String bio;

    private PersonalDetail personalDetail;

    private Premium premium;

    private Network network;

    private List<Role> roles;

    @Field(name = "created_date")
    private LocalDateTime createdAt;

    @Field(name = "updated_date")
    private LocalDateTime updatedAt;

}
