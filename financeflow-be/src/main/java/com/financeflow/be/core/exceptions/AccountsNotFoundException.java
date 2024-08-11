package com.financeflow.be.core.exceptions;

public class AccountsNotFoundException  extends Exception {
    public AccountsNotFoundException() {
        super("Accounts not found.");
    }
}
