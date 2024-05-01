package com.greethy.finance.infrastructure.entity;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Income {

    private String name;

    private BigDecimal value;
}
