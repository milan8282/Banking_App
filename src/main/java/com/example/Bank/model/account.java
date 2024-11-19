package com.example.Bank.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class account {

    @Id
    @Column(length = 10)
    private String accountNumber;
    private double amount;
    private String accountHolder;

    public account(String accountNumber, double amount, String accountHolder) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.accountHolder = accountHolder;
    }

    public account() {}

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }
}
