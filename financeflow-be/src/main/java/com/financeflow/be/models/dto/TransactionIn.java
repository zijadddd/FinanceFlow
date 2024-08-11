package com.financeflow.be.models.dto;

import com.financeflow.be.core.ExpenseType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionIn {
    private String description;
    private String expense;
    private Integer amount;
}
