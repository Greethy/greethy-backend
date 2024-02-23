package com.greethy.finance.infrastructure.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;

@Data
@Document
public class MonthlyIncome {

    @Id
    private String id;

    private Currency currency;

    @Field(name = "projected_income")
    private List<Income> projectedIncomes;

    private BigDecimal projectedTotal;

    @Field(name = "actual_incomes")
    private List<Income> actualIncomes;

    private BigDecimal actualTotal;

    private BigDecimal different;

    @Field(name = "created_time")
    private LocalDateTime createdTime;

    @Field(name = "updated_time")
    private LocalDateTime updatedTime;

}
