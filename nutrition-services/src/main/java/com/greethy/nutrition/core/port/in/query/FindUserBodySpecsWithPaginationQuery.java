package com.greethy.nutrition.core.port.in.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FindUserBodySpecsWithPaginationQuery {

    private String userId;

    private int offset;

    private int limit;
}
