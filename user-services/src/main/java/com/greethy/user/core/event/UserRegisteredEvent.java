package com.greethy.user.core.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class UserRegisteredEvent {

    private String userId;

    private String username;

    private String email;

    private String password;

    private List<String> roles;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;



}
