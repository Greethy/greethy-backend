package com.greethy.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@RedisHash
@Data
@Builder
@AllArgsConstructor
public class Token {

    private String id;

    private String username;

}
