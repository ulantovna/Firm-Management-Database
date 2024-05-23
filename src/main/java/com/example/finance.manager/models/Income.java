package com.example.financemanager.models;

public class Income {
    private double amount;
    private String description;

    public Income(double amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
}
