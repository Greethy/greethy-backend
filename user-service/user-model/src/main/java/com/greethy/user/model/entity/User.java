package com.greethy.user.model.entity;

import com.greethy.core.domain.entity.BaseEntity;
import com.greethy.user.model.entity.value.PersonalDetail;
import com.greethy.user.model.entity.value.Premium;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represent for user, including login information,
 * personal information, body specified, and referenced ids to other documents.
 *
 * @author Kien N.Thanh
 */

@Data
@NoArgsConstructor
@Document(collection = "users")
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    @MongoId
    private String id;

    @Indexed(unique = true)
    private String username;

    @Indexed(unique = true)
    private String email;

    private Boolean verified = Boolean.FALSE;

    private String password;

    private String avatar;

    @Field(name = "banner_image")
    private String bannerImage;

    private String bio;

    @Field(name = "personal_info")
    private PersonalDetail personalDetail;

    private Premium premium;

    @DocumentReference
    private Networking networking;

    private List<Role> roles;

    @Field(name = "body_specs_ids")
    private List<String> bodySpecsIds = new ArrayList<>();
}
