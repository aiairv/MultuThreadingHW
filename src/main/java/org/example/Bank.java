package org.example;

import java.io.InterruptedIOException;
import java.util.Random;
public class Bank {
    private static final int numberOfAccounts = 10;
    private static final int beginningBalance = 1000;

    public static void main(String[] args) throws InterruptedException {
        Account[] accounts = new Account[numberOfAccounts];
        for (int i = 0; i < numberOfAccounts; i++) {
            accounts[i] = new Account(beginningBalance, i);
        }
        Thread[] threads = new Thread[numberOfAccounts*2];
        Random random = new Random();
        for (int i = 0; i < threads.length; i++) {
            int fromAccount = random.nextInt(numberOfAccounts);
            int toAccount = random.nextInt(numberOfAccounts);
            int amount = random.nextInt(beginningBalance);
            if (i % 2 == 0){
                threads[i] = new Thread(()->transfer(accounts[fromAccount], accounts[toAccount], amount));
            }else{
                threads[i] = new Thread(()->withdraw(accounts[fromAccount],amount));
            }
            threads[i].start();
        }
        for (Thread thread : threads){
            thread.join();
        }
        for (Account account : accounts){
            System.out.println("Account " + account.getAccountNumber() + "balance " + account.getBalance());
        }
    }
    public static void transfer(Account from, Account to, int amount){
        from.withdraw(amount);
        to.deposit(amount);
    }
    public static void withdraw(Account account, int amount){
        while (!account.withdraw(amount));
    }
}
