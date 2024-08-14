package com.financeflow.be.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionOut {
    private String accountName;
    private String description;
    private String expense;
    private Double amount;
    private String currencyCode;
    private Double defaultAmount;
    private String defaultCurrencyCode;
    private String processedAt;

    public TransactionOut(String accountName, String description, String expense, Double amount, String currencyCode, String processedAt) {
        this.accountName = accountName;
        this.description = description;
        this.expense = expense;
        this.amount = amount;
        this.currencyCode = currencyCode;
        this.defaultAmount = amount;
        this.defaultCurrencyCode = currencyCode;
        this.processedAt = processedAt;
    }
}
