package com.greethy.core.domain.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FindUserBodySpecsIdsQuery {

    private String userId;
}
