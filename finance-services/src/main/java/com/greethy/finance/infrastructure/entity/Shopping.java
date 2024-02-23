package com.greethy.finance.infrastructure.entity;

import com.greethy.finance.infrastructure.entity.enums.ExpenseType;
import lombok.Data;

@Data
public class Shopping {

    private String name;

    private ExpenseType expenseType;

}
