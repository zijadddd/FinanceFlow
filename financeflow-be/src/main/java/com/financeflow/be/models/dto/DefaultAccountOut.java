package com.financeflow.be.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DefaultAccountOut {
    private Double balance;
    private String currencyCode;
}
