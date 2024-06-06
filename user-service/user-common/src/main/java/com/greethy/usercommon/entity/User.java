package com.greethy.usercommon.entity;

import com.greethy.common.domain.entity.BaseEntity;
import com.greethy.usercommon.entity.value.PersonalDetail;
import com.greethy.usercommon.entity.value.Premium;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
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

    @Field(name = "avatar_url")
    private String avatarUrl;

    @Field(name = "banner_url")
    private String bannerUrl;

    private String bio;

    private Boolean status;

    @Field(name = "personal_detail")
    private PersonalDetail personalDetail;

    private Premium premium;

    @Field(name = "networking_id")
    private String networkingId;

    private List<Role> roles;

    @Field(name = "body_specs_ids")
    private List<String> bodySpecsIds = new ArrayList<>();
}
