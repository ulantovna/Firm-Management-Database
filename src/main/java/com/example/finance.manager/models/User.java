package com.example.financemanager.models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {
    protected String username;
    private String passwordHash;
    protected ArrayList<Income> incomes;
    protected ArrayList<Expense> expenses;
    protected ArrayList<Savings> savings;
    protected ArrayList<Goal> goals;

    public User(String username, String password) {
        this.username = username;
        this.passwordHash = hashPassword(password);
        this.incomes = new ArrayList<>();
        this.expenses = new ArrayList<>();
        this.savings = new ArrayList<>();
        this.goals = new ArrayList<>();
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkPassword(String password) {
        return this.passwordHash.equals(hashPassword(password));
    }

    public void addIncome(double amount, String description) {
        incomes.add(new Income(amount, description));
    }

    public void addExpense(double amount, String description) {
        expenses.add(new Expense(amount, description));
    }

    public void setGoal(String description, double targetAmount) {
        goals.add(new Goal(description, targetAmount));
    }

    public void viewReport() {
        double totalIncome = incomes.stream().mapToDouble(Income::getAmount).sum();
        double totalExpense = expenses.stream().mapToDouble(Expense::getAmount).sum();
        double balance = totalIncome - totalExpense;

        System.out.println("Financial Report:");
        System.out.println("Total Income: $" + totalIncome);
        System.out.println("Total Expenses: $" + totalExpense);
        System.out.println("Balance: $" + balance);
    }
}