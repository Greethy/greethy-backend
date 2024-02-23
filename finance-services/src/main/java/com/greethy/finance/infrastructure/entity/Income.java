package com.greethy.finance.infrastructure.entity;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class Income {

    private String name;

    private BigDecimal value;

}
