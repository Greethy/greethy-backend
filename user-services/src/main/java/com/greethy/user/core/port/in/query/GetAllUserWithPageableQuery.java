package com.greethy.user.core.port.in.query;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAllUserWithPageableQuery {

    private int page;

    private int size;

}
