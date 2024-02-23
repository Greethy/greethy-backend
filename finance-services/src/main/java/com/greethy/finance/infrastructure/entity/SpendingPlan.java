package com.greethy.finance.infrastructure.entity;

import com.greethy.finance.infrastructure.entity.enums.ExpenseType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SpendingPlan {

    private ExpenseType type;

    private LocalDate spendingDay;

}
