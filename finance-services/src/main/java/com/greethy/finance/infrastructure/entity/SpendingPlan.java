package com.greethy.finance.infrastructure.entity;

import java.time.LocalDate;

import com.greethy.finance.infrastructure.entity.enums.ExpenseType;

import lombok.Data;

@Data
public class SpendingPlan {

    private ExpenseType type;

    private LocalDate spendingDay;
}
