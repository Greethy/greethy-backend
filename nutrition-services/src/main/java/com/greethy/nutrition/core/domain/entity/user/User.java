package com.greethy.nutrition.core.domain.entity.user;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document
public class User {

    @Id
    private String id;

    private List<String> bodySpecsIds;

    @Field
    private LocalDateTime updatedAt;

}
