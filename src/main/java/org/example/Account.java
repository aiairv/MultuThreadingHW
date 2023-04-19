package org.example;

import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private int balance;
    private int accountNumber;
    private ReentrantLock lock;

    public Account(int balance, int accountNumber){
        this.balance = balance;
        this.accountNumber = accountNumber;
        lock = new ReentrantLock();
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public void setLock(ReentrantLock lock) {
        this.lock = lock;
    }
    public void deposit(int amount){
        lock.lock();
        try {
            balance += amount;
        }finally {
            lock.unlock();
        }
    }
    public boolean withdraw(int amount){
        lock.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
                return true;
            }
            return false;
        }finally {
            lock.unlock();
        }

    }
}
