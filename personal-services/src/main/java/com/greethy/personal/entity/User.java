package com.greethy.personal.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
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

    private String username;

    private String email;

    private String password;

    private Profile profile;

}
