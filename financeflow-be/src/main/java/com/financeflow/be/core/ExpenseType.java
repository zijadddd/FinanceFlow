package com.financeflow.be.core;

import com.financeflow.be.core.exceptions.ExpenseNotFoundException;

public enum ExpenseType {
    FOOD("Food"),
    HOUSING("Housing"),
    ENTERTAINMENT("Entertainment"),
    HEALTH("Health"),
    EDUCATION("Education"),
    SHOPPING("Shopping"),
    PERSONAL_SERVICES("Personal Services"),
    TAXES("Taxes"),
    DONATIONS("Donations"),
    TRANSFER("Transfer");

    private final String name;

    ExpenseType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static ExpenseType fromString(String displayName) throws ExpenseNotFoundException {
        for (ExpenseType type : ExpenseType.values()) {
            if (type.toString().equalsIgnoreCase(displayName)) {
                return type;
            }
        }
        throw new ExpenseNotFoundException(displayName);
    }
}
