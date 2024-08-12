package com.financeflow.be.models.dto;

import com.financeflow.be.models.dao.Account;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AccountOut {
    private String id;
    private String firstName;
    private String lastName;
    private Double balance;
    private String currencyCode;
    private LocalDateTime createdAt;

    public AccountOut(Account account) {
        this.id = account.getId().toString();
        this.firstName = account.getFirstName();
        this.lastName = account.getLastName();
        this.balance = account.getBalance();
        this.currencyCode = account.getCurrencyCode();
        this.createdAt = account.getCreatedAt();
    }
}
