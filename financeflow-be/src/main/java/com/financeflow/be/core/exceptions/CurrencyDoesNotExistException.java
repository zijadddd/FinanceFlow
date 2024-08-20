package com.financeflow.be.core.exceptions;

public class CurrencyDoesNotExistException extends Exception {
    public CurrencyDoesNotExistException(String currencyCode) {
        super("Currency " + currencyCode + " does not exist.");
    }
}
