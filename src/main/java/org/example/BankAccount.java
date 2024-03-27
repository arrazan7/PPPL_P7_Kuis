package org.example;

public class BankAccount {
    private double balance;
    private String accountNumber;

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Deposit amount cannot be negative.");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds for withdraw");
        } else {
            balance -= amount;
        }
    }

    public void transferFunds(BankAccount recipient, double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds for transfer.");
        } else {
            withdraw(amount);
            recipient.deposit(amount);
        }
    }

}

