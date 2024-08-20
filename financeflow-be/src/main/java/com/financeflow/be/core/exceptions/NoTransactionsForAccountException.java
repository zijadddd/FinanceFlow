package com.financeflow.be.core.exceptions;

public class NoTransactionsForAccountException extends Exception {
    public NoTransactionsForAccountException(String name) {
        super("There is no transactions for account " + name + ".");
    }
}
