package com.greethy.nutrition.core.port.in.query;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindBodySpecsWithPaginationQuery {

    private int offset;

    private int limit;
}
