package com.greethy.user.core.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "networking")
public class Network {

    @Id
    private String id;

    private List<String> following;

    private List<String> followers;

}
