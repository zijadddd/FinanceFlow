package com.financeflow.be.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountIn {
    private String firstName;
    private String lastName;
    private Double balance;
    private String currencyCode;
    private Integer defaultAccountId;
}
