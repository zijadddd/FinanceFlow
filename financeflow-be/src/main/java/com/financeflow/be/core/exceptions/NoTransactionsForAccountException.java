package com.financeflow.be.core.exceptions;

public class NoTransactionsForAccountException extends Exception {
    public NoTransactionsForAccountException(Integer id) {
        super("There is no transactions for account with id " + id);
    }
}
