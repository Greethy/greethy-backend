package com.greethy.nutritioncommon.dto.request.query;

import lombok.Builder;

@Builder
public record SearchByNameQuery (String name, Integer offset, Integer limit, String sort) {
}
