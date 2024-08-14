package com.financeflow.be.models.dto;

import com.financeflow.be.models.dao.Account;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AccountOut {
    private Integer id;
    private String name;
    private Double balance;
    private String currencyCode;
    private LocalDateTime createdAt;
    private Double defaultBalance;
    private String defaultCurrencyCode;

    public AccountOut(Account account, Double defaultBalance, String defaultCurrencyCode) {
        this.id = account.getId();
        this.name = account.getName();
        this.balance = account.getBalance();
        this.currencyCode = account.getCurrencyCode();
        this.createdAt = account.getCreatedAt();
        this.defaultBalance = defaultBalance;
        this.defaultCurrencyCode = defaultCurrencyCode;
    }
}
