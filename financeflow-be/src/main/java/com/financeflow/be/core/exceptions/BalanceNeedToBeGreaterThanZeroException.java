package com.financeflow.be.core.exceptions;

public class BalanceNeedToBeGreaterThanZeroException extends Exception{
    public BalanceNeedToBeGreaterThanZeroException() {
        super("Balance need to be greater than 0.");
    }
}
