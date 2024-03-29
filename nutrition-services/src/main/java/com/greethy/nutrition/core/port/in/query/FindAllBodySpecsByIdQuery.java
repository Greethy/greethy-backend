package com.greethy.nutrition.core.port.in.query;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FindAllBodySpecsByIdQuery {

    private List<String> bodySpecsIds;

}
