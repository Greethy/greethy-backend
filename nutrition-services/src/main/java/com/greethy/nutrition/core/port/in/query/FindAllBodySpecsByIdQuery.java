package com.greethy.nutrition.core.port.in.query;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindAllBodySpecsByIdQuery {

    private List<String> bodySpecsIds;
}
