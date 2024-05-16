package com.greethy.nutrition.api.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.greethy.nutrition.core.domain.value.Bmi;
import com.greethy.nutrition.core.domain.value.Bmr;
import com.greethy.nutrition.core.domain.value.Pal;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class BodySpecsResponse {

    private Integer age;

    private Double height;

    private Double weight;

    private String gender;

    private Bmi bmi;

    private Bmr bmr;

    private Pal pal;

    private Double tdee;

    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @JsonProperty("updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;
}
