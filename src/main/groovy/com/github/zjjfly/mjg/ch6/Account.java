package com.github.zjjfly.mjg.ch6;

/**
 * @author <a href="https://github.com/zjjfly"/>zjjfly</a>
 */
public class Account {

    private int id;

    private Double balance;

    public Account() {}

    public Account(int id, Double balance) {
        this.id = id;
        this.balance = balance;
    }

    public void withdraw(double amount) {
        this.balance -= amount;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public int getId() {
        return id;
    }

    public Double getBalance() {
        return balance;
    }
}
