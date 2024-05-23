package com.example.financemanager.models;

public class Goal {
    private String description;
    private double targetAmount;

    public Goal(String description, double targetAmount) {
        this.description = description;
        this.targetAmount = targetAmount;
    }

    public String getDescription() {
        return description;
    }

    public double getTargetAmount() {
        return targetAmount;
    }
}
