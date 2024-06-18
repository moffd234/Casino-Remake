package com.github.zipcodewilmington.casino;

import com.github.zipcodewilmington.utils.FileLogger;

/**
 * Created by leon on 7/21/2020.
 * `ArcadeAccount` is registered for each user of the `Arcade`.
 * The `ArcadeAccount` is used to log into the system to select a `Game` to play.
 */
public class CasinoAccount {
    private String username;
    private String password;
    private double balance;
    private CasinoAccountManager accountManager;

    public CasinoAccount() {
    }

    public CasinoAccount(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0;
    }

    public CasinoAccount(String username, String password, double balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    public double addWinnings(double wager) {
        if(wager <= 0){
            throw new IllegalArgumentException("Wager must be a positive number");
        }
        FileLogger.logInfo("Adding winnings: " + wager + " to balance: " + balance + ". New balance: "
                + (balance + wager) + " for account: " + username);
        balance += wager;
        writeToCSV();
        return balance;
    }

    public double subtractLosses(double wager) {
        if(wager <= 0){
            throw new IllegalArgumentException("Wager must be a positive number");
        }
        FileLogger.logInfo("Subtracting loses: " + wager + " from balance: " + balance + ". New balance: "
                + (balance - wager) + " for account: " + username);
        balance -= wager;
        writeToCSV();
        return balance;
    }

    public double updateBalance(double amount) {
        FileLogger.logInfo("Adding: " + amount + "to balance " + balance + "new balance: "
                + (balance + amount) + " for account: " + username);
        balance += amount;

        writeToCSV();
        return balance;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setPassword(String password) {
        this.password = password;
        FileLogger.logInfo("Password has been reset for account: " + username);
        writeToCSV();
    }

    public void setAccountManager(CasinoAccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public void writeToCSV() {
        if (accountManager != null) {
            FileLogger.logInfo("Writing new account info for account: " + username + " to CSV");
            accountManager.writeAccountsToCSV();
            FileLogger.logInfo("Successfully wrote update");
        }
    }

    @Override
    public String toString() {
        return "CasinoAccount{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                '}';
    }
}
