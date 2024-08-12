package com.financeflow.be.core.exceptions;

public class ExpenseNotFoundException extends Exception {
    public ExpenseNotFoundException(String expense) {
        super("Expense " + expense + " not found");
    }
}
