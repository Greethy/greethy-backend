package com.greethy.core.domain.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class FindUserBodySpecsIdsPaginationQuery {

    private String userId;

    private int offset;

    private int limit;

}
