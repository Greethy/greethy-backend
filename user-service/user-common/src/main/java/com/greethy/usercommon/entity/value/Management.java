package com.greethy.usercommon.entity.value;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class Management {

    @Field(name = "body_spec_analysis_id")
    private String bodySpecAnalysisId;

}
