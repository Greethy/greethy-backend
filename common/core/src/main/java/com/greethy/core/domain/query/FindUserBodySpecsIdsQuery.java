package com.greethy.core.domain.query;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindUserBodySpecsIdsQuery {

    private String userId;

}
