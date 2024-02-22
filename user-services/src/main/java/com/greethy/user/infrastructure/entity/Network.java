package com.greethy.user.infrastructure.entity;

import lombok.Data;

import java.util.List;

@Data
public class Network {

    private List<String> following;

    private List<String> followers;

}
