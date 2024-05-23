package com.example.financemanager.models;

public class Savings {
    private double amount;
    private String description;

    public Savings(double amount, String description) {
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
