package com.github.zipcodewilmington.casino;

/**
 * Created by leon on 7/21/2020.
 * `ArcadeAccount` is registered for each user of the `Arcade`.
 * The `ArcadeAccount` is used to log into the system to select a `Game` to play.
 */
public class CasinoAccount {
    private String username;
    private String password;
    private double balance;

    public CasinoAccount(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0;
    }

    public CasinoAccount() {
    }

    public double addWinnings(int wager) {
        return 0;
    }

    public double subtractLosses(int wager) {
        return 0;
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
}
