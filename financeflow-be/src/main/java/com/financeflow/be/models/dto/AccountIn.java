package com.financeflow.be.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountIn {
    private String name;
    private Double balance;
    private String currencyCode;
    private Integer defaultAccountId;
}
