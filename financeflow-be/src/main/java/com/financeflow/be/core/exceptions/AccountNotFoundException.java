package com.financeflow.be.core.exceptions;

public class AccountNotFoundException  extends Exception {
    public AccountNotFoundException(int id) {
        super("Account with id " + id + " not found.");
    }
}
