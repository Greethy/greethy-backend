package com.greethy.personal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    private String gmail;

    @Field(name = "phone_number")
    private String phoneNumber;

    @Field(name = "instagram_url")
    private String instagramUrl;

    @Field(name = "facebook_url")
    private String facebookUrl;

}
