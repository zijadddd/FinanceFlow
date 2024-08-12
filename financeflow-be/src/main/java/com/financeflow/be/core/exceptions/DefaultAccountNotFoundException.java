package com.financeflow.be.core.exceptions;

public class DefaultAccountNotFoundException extends Exception {
    public DefaultAccountNotFoundException() {
        super("Default account not found.");
    }
}
