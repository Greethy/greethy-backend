package com.greethy.personal.application.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

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

    private String password;

    private Profile profile;

    @DBRef
    private Network network;

}
