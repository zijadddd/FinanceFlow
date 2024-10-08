package com.financeflow.be.core.exceptions;

public class AccountNotFoundException  extends Exception {
    public AccountNotFoundException(Integer id) {
        super("Account with id " + id + " not found.");
    }
}
