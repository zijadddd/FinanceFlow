package com.financeflow.be.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionOut {
    private String accountName;
    private String description;
    private Double amount;
    private String currencyCode;
    private Double defaultAmount;
    private String defaultCurrencyCode;

    public TransactionOut(String accountName, String description, Double amount, String currencyCode) {
        this.accountName = accountName;
        this.description = description;
        this.amount = amount;
        this.currencyCode = currencyCode;
        this.defaultAmount = amount;
        this.defaultCurrencyCode = currencyCode;
    }
}
