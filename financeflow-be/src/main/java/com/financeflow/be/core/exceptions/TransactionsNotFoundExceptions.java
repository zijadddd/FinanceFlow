package com.financeflow.be.core.exceptions;

public class TransactionsNotFoundExceptions extends Exception {
    public TransactionsNotFoundExceptions() {
        super("Transactions not found.");
    }
}
