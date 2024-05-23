package com.example.financemanager;

import com.example.financemanager.models.*;

import java.util.ArrayList;
import java.util.Scanner;

public class FinanceManagerApp {
    private static ArrayList<User> users = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void registerUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Are you a Financial Analyst? (yes/no): ");
        String isAnalyst = scanner.nextLine();

        User user;
        if (isAnalyst.equalsIgnoreCase("yes")) {
            user = new FinancialAnalyst(username, password);
        } else {
            user = new RegularUser(username, password);
        }

        users.add(user);
        System.out.println("User registered successfully.");
    }

    private static void loginUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = users.stream()
                .filter(u -> u.username.equals(username) && u.checkPassword(password))
                .findFirst()
                .orElse(null);

        if (user == null) {
            System.out.println("Invalid username or password.");
            return;
        }

        if (user instanceof FinancialAnalyst) {
            financialAnalystMenu((FinancialAnalyst) user);
        } else {
            regularUserMenu(user);
        }
    }

    private static void regularUserMenu(User user) {
        while (true) {
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. Set Goal");
            System.out.println("4. View Report");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter amount: ");
                    double incomeAmount = scanner.nextDouble();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter description: ");
                    String incomeDescription = scanner.nextLine();
                    user.addIncome(incomeAmount, incomeDescription);
                    System.out.println("Income added successfully.");
                    break;
                case 2:
                    System.out.print("Enter amount: ");
                    double expenseAmount = scanner.nextDouble();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter description: ");
                    String expenseDescription = scanner.nextLine();
                    user.addExpense(expenseAmount, expenseDescription);
                    System.out.println("Expense added successfully.");
                    break;
                case 3:
                    System.out.print("Enter goal description: ");
                    String goalDescription = scanner.nextLine();
                    System.out.print("Enter target amount: ");
                    double targetAmount = scanner.nextDouble();
                    scanner.nextLine();  // Consume newline
                    user.setGoal(goalDescription, targetAmount);
                    System.out.println("Goal set successfully.");
                    break;
                case 4:
                    user.viewReport();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void financialAnalystMenu(FinancialAnalyst analyst) {
        while (true)
        System.out.println("1. Provide Recommendations");
            System.out.println("2. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter username to analyze: ");
                    String username = scanner.nextLine();
                    User user = users.stream()
                            .filter(u -> u.username.equals(username))
                            .findFirst()
                            .orElse(null);

                    if (user == null) {
                        System.out.println("User not found.");
                    } else {
                        analyst.provideRecommendations(user);
                    }
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
