package com.greethy.user.api.rest.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetAllUserWithPageableRequest {

    private int page = 0;

    private int size = 10;

    private String sort = "userId";

}
