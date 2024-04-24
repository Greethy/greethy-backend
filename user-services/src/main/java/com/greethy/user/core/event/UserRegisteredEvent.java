package com.greethy.user.core.event;

import com.greethy.user.core.domain.entity.Networking;
import com.greethy.user.core.domain.value.PersonalDetail;
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

    private PersonalDetail personalDetail;

    private Networking networking;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;



}
