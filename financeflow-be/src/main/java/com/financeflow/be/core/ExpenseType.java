package com.financeflow.be.core;

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
}
