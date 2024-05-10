package com.greethy.nutrition.core.port.in.query;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindFoodWithPaginationQuery {

    private int offset;

    private int limit;
}
