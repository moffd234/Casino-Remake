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
                + (balance + wager) + " for account: " + this.username);
        balance += wager;
        writeBalanceToCSV();
        return balance;
    }

    public double subtractLosses(double wager) {
        if(wager <= 0){
            throw new IllegalArgumentException("Wager must be a positive number");
        }
        FileLogger.logInfo("Subtracting loses: " + wager + " from balance: " + balance + ". New balance: "
                + (balance - wager) + " for account: " + this.username);
        balance -= wager;
        writeBalanceToCSV();
        return balance;
    }

    public double updateBalance(double amount) {
        FileLogger.logInfo("Updating balance from" + balance + " to "
                + (balance + amount) + " for account: " + this.username);
        balance += amount;

        writeBalanceToCSV();
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
    }

    public void setAccountManager(CasinoAccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public void writeBalanceToCSV() {
        if (accountManager != null) {
            FileLogger.logInfo("Writing new balance for account: " + this.username + " to CSV");
            accountManager.writeAccountsToCSV();
            FileLogger.logInfo("Successfully wrote balance update");
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
