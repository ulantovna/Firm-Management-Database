package com.example.financemanager.models;

public class FinancialAnalyst extends User {
    public FinancialAnalyst(String username, String password) {
        super(username, password);
    }

    public void provideRecommendations(User user) {
        double totalIncome = user.incomes.stream().mapToDouble(Income::getAmount).sum();
        double totalExpense = user.expenses.stream().mapToDouble(Expense::getAmount).sum();
        double balance = totalIncome - totalExpense;

        System.out.println("Recommendations:");
        if (balance < 0) {
            System.out.println("Your expenses exceed your income. Consider reducing unnecessary expenses.");
        } else {
            System.out.println("You have a positive balance. Consider investing or saving more.");
        }
    }
}
