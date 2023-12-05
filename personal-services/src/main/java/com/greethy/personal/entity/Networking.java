package com.greethy.personal.entity;

import lombok.Data;

import java.util.List;

@Data
public class Networking {

    private List<String> following;

    private List<String> followers;

}
