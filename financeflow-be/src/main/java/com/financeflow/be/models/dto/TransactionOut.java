package com.financeflow.be.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionOut {
    private String accountName;
    private String description;
    private Integer amount;
    private String currencyCode;
}
